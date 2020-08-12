package com.upc.lw.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.lw.base.BaseDto;
import com.upc.lw.system.Dept;
import com.upc.lw.system.Job;
import com.upc.lw.system.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:24
 */
@Data
public class UserDto extends BaseDto implements Serializable {
    private String id;

    private String pin;

    @JsonIgnore
    private String password;

    private String nickName;

    private Dept dept;

    private Set<Role> roles;

    private Set<Job> jobs;

    private String phone;

    private String email;

    private int status;

}
