package com.upc.lw.moudules.security.service;

import com.alibaba.fastjson.JSON;
import com.upc.lw.moudules.security.config.bean.SecurityProperties;
import com.upc.lw.security.dto.JwtUserDto;
import com.upc.lw.utills.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description 记录登录用户
 * @author: liwei
 * @date: 2020/8/12 9:39
 */
@Slf4j
@Component
public class OnlineUserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SecurityProperties securityProperties;

    public void saveOnlineUserInfo(JwtUserDto userDto, String token) {
        redisUtils.set(securityProperties.getOnlineKey() + token, JSON.toJSONString(userDto), securityProperties.getExpireMinutes(), TimeUnit.MINUTES);
    }

    public JwtUserDto getOnlineUserInfo(String token) {
        JwtUserDto ret = null;
        String value = redisUtils.get(securityProperties.getOnlineKey() + token);
        if (StringUtils.isNotEmpty(value)) {
            ret = JSON.parseObject(value, JwtUserDto.class);
        }
        log.info("getOnlineUserInfo token:{},ret:{}", token, ret);
        return ret;
    }

    public Boolean deleteOnlineUserInfo(String token) {
        Boolean ret = redisUtils.del(securityProperties.getOnlineKey() + token);
        return ret;
    }
}
