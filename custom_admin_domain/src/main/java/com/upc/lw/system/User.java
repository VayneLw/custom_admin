package com.upc.lw.system;

import com.upc.lw.base.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description 用户表
 * @author: liwei
 * @date: 2020/8/6 11:16
 */
@Entity
@Table(name = "sys_user")
@Data
public class User extends BaseEntity {

    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String pin;

    /**
     * 使用security加密
     */
    private String password;

    private String nickName;

    private String phone;

    private String email;

    /**
     * 状态 0失效  1 有效
     */
    private int enabled;

}
