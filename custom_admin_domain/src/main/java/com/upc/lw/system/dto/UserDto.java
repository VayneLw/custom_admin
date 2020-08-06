package com.upc.lw.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.lw.base.BaseDto;
import lombok.Data;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:24
 */
@Data
public class UserDto extends BaseDto {
    private String pin;

    @JsonIgnore
    private String password;

    private String nickName;

    private String phone;

    private String email;

    private int enabled;

}
