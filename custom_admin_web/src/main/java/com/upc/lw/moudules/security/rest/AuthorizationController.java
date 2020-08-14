package com.upc.lw.moudules.security.rest;

import cn.hutool.core.util.IdUtil;
import com.upc.lw.annotation.Log;
import com.upc.lw.moudules.security.config.TokenProvider;
import com.upc.lw.moudules.security.config.bean.LoginProperties;
import com.upc.lw.moudules.security.config.bean.SecurityProperties;
import com.upc.lw.moudules.security.mq.LoginMessageProducer;
import com.upc.lw.moudules.security.service.OnlineUserService;
import com.upc.lw.request.LoginRequest;
import com.upc.lw.response.ResponseUtil;
import com.upc.lw.security.dto.JwtUserDto;
import com.upc.lw.utills.RedisUtils;
import com.upc.lw.utills.RsaUtils;
import com.upc.lw.utills.SecurityUtils;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description 登录接口
 * @author: liwei
 * @date: 2020/8/5 11:16
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private OnlineUserService onlineUserService;

    @Autowired
    private LoginMessageProducer loginMessageProducer;

    @Value("${rsa.private.key}")
    private String rsaPrivateKey;

    @RequestMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        if (StringUtils.isEmpty(loginRequest.getUuid())) {
            return ResponseEntity.badRequest().body(ResponseUtil.paramFail());
        }

        try {
            //密码解密
            String password = RsaUtils.decryptByPrivateKey(rsaPrivateKey, loginRequest.getPassword());

            //校验验证码
            String code = redisUtils.get(loginRequest.getUuid());
            redisUtils.del(loginRequest.getUuid());
            if (StringUtils.isEmpty(code)) {
                log.info("验证码不存在或已过期");
                return ResponseEntity.badRequest().body(ResponseUtil.fail());
            }

            if (StringUtils.isEmpty(loginRequest.getCode()) || !StringUtils.equalsIgnoreCase(code, loginRequest.getCode())) {
                log.info("验证码错误");
                return ResponseEntity.badRequest().body(ResponseUtil.fail());
            }

            //密码验证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), password);
            Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            //返回结果
            String token = tokenProvider.createToken(authenticate);
            JwtUserDto jwtUserDto = (JwtUserDto) authenticate.getPrincipal();
            onlineUserService.saveOnlineUserInfo(jwtUserDto, token);

            Map<String, Object> ret = new HashMap(2);
            ret.put("token", securityProperties.getTokenStartWith() + token);
            ret.put("user", jwtUserDto);
            return ResponseEntity.ok(ret);
        } catch (Exception e) {
            log.error("login fail error:", e);
            return ResponseEntity.badRequest().body(ResponseUtil.fail());
        }
    }

    @Log(value = "获取验证码")
    @RequestMapping("/code")
    public ResponseEntity<Object> getCode() {
        Map<String, Object> imgRet;
        try {
            Captcha captcha = loginProperties.getCaptcha();
            String uuid = IdUtil.simpleUUID();

            redisUtils.set(uuid, captcha.text(), loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
            imgRet = new HashMap<>(2);
            imgRet.put("img", captcha.toBase64());
            imgRet.put("uuid", uuid);
            log.info("getCode uuid:{},codeText:{}", uuid, captcha.text());

            loginMessageProducer.sendMessage("test=====lw====");
        } catch (Exception e) {
            log.error("getCode error:", e);
            return ResponseEntity.badRequest().body(ResponseUtil.fail());
        }
        return ResponseEntity.ok(imgRet);
    }

    @RequestMapping("/info")
    public ResponseEntity<Object> getUserInfo() {
        UserDetails user = SecurityUtils.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    @RequestMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        String token = tokenProvider.resolveToken(request);
        Boolean ret = onlineUserService.deleteOnlineUserInfo(token);
        log.info("logout token:{},ret:{}", token, ret);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
