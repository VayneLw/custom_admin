package com.upc.lw.moudules.security.service;

import com.upc.lw.moudules.system.service.UserService;
import com.upc.lw.security.dto.JwtUserDto;
import com.upc.lw.system.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 18:05
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto userDto = userService.findByPhoneOrPin(s);
        JwtUserDto jwtUserDto = new JwtUserDto();

        jwtUserDto.setUser(userDto);

        return jwtUserDto;
    }
}
