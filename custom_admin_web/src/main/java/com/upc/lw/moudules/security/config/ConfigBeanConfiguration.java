package com.upc.lw.moudules.security.config;

import com.upc.lw.moudules.security.config.bean.LoginProperties;
import com.upc.lw.moudules.security.config.bean.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置文件转换pojo
 * @author: liwei
 * @date: 2020/8/5 11:24
 */
@Configuration
public class ConfigBeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "login")
    public LoginProperties getLoginProperties() {
        return new LoginProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public SecurityProperties getSecurityProperties() {
        return new SecurityProperties();
    }

}

