package com.upc.lw.moudules.system.service;

import com.upc.lw.system.dto.UserDto;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:06
 */
public interface UserService {
    UserDto findByPhone(String phone);
}
