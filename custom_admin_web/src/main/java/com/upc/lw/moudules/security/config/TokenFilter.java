package com.upc.lw.moudules.security.config;

import com.google.common.collect.Lists;
import com.upc.lw.exception.TokenNotFoundException;
import com.upc.lw.moudules.security.config.bean.SecurityProperties;
import com.upc.lw.moudules.security.service.OnlineUserService;
import com.upc.lw.security.dto.JwtUserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

/**
 * @Description token校验
 * 1.先只做成校验缓存中token信息
 * @author: liwei
 * @date: 2020/8/12 13:46
 */
@Slf4j
@Component
public class TokenFilter extends GenericFilterBean {

    @Autowired
    private OnlineUserService onlineUserService;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = resolveToken(request);
        if (StringUtils.isNotEmpty(token)) {
            JwtUserDto userInfo = onlineUserService.getOnlineUserInfo(token);
            if (userInfo == null) {
                throw new TokenNotFoundException();
            }
            //暂不做权限功能处理，统一admin
            Collection<? extends GrantedAuthority> authorities = Lists.newArrayList(new SimpleGrantedAuthority("admin"));
            UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(userInfo, token, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(securityProperties.getHeader());
        String token = "";
        if (StringUtils.isNotEmpty(header) && header.startsWith(securityProperties.getTokenStartWith())) {
            token = header.replace(securityProperties.getTokenStartWith(), "");
        }
        log.info("resolveToken header:{},token:{}", header, token);
        return token;
    }

}
