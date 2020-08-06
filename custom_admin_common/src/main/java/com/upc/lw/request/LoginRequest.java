package com.upc.lw.request;

import lombok.Data;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/5 16:37
 */
@Data
public class LoginRequest {
    private String username;

    private String password;

    private String code;

    private String uuid;

}
