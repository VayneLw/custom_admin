package com.upc.lw.moudules.security.config;

import com.upc.lw.moudules.security.config.bean.LoginProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description  配置文件转换pojo
 * @author: liwei
 * @date: 2020/8/5 11:24
 */
@Configuration
public class ConfigBeanConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "login")
    public LoginProperties getLoginProperties(){
          return new LoginProperties();
    }

    /**
     * 1.关闭 CSRF 保护 : csrf默认是开启得，开启会拦截post请求（403）
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
    }



}

