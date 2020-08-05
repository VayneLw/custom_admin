package com.upc.lw.moudules.security.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Map;

/**
 * @Description 登录接口
 * @author: liwei
 * @date: 2020/8/5 11:16
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @RequestMapping("/code")
    public Response<Map> getCode() {

        return null;
    }
}
