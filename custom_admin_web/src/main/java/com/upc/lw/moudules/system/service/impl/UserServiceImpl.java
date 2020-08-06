package com.upc.lw.moudules.system.service.impl;

import com.upc.lw.exception.EntityNotFoundException;
import com.upc.lw.moudules.system.service.UserService;
import com.upc.lw.system.User;
import com.upc.lw.system.dto.UserDto;
import com.upc.lw.system.mapstruct.UserMapper;
import com.upc.lw.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:07
 */
@Service
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto findByPhone(String phone) {
        User user = userRepository.findByPhone(phone);
        if(user==null){
           throw new EntityNotFoundException(User.class);
        }else{
            return userMapper.toDto(user);
        }
    }
}
