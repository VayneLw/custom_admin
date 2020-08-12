package com.upc.lw.moudules.security.config;

import cn.hutool.core.util.IdUtil;
import com.upc.lw.moudules.security.config.bean.SecurityProperties;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 11:22
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class TokenProvider {

    @Autowired
    private SecurityProperties securityProperties;

    public static final String AUTHORITIES_KEY = "auth";


    public String createToken(Authentication authenticate){
        return Jwts.builder()
                .setId(IdUtil.simpleUUID())
                .claim(AUTHORITIES_KEY, "admin")
                .setSubject(authenticate.getName())
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(securityProperties.getHeader());
        String token = "";
        if (StringUtils.isNotEmpty(header) && header.startsWith(securityProperties.getTokenStartWith())) {
            token = header.replace(securityProperties.getTokenStartWith(), "");
        }
        log.info("resolveToken header:{},token:{}", header, token);
        return token;
    }
}
