package com.upc.lw.moudules.system.service;

import com.upc.lw.request.user.UserRequest;
import com.upc.lw.system.dto.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:06
 */
public interface UserService {
    UserDto findByPhoneOrPin(String info);

    Map<String,Object> findUsers(Pageable pageable);

    Map<String,Object> findUserListByArg(UserRequest.QueryArg arg, Pageable pageable);
}
