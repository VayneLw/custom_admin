package com.upc.lw.moudules.system.rest;

import com.upc.lw.moudules.system.service.UserService;
import com.upc.lw.request.user.UserRequest;
import com.upc.lw.utills.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description 用户信息--对外接口
 * @author: liwei
 * @date: 2020/8/13 15:36
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> userList(UserRequest.QueryArg arg, Pageable pageable) {
        try {
            Map<String, Object> map = userService.findUserListByArg(arg, pageable);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            log.info("userList error:{}", e);
        }
        return new ResponseEntity<>(PageUtils.toPage(null, 0), HttpStatus.OK);
    }

}
