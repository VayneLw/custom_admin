package com.upc.lw.system;

import com.upc.lw.base.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * @Description 用户表
 * @author: liwei
 * @date: 2020/8/6 11:16
 */
@Entity
@Table(name = "sys_user")
@Data
public class User extends BaseEntity implements Serializable {

    @Id
    @Column(name = "user_id")
    @NotBlank
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String pin;

    @ManyToMany
    @JoinTable(name="sys_user_roles",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName="user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName="id")})
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name="sys_user_jobs",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName="user_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id",referencedColumnName="id")})
    private Set<Job> jobs;

    /**
     * 使用security加密
     */
    @NotBlank
    private String password;

    private String nickName;


    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    @NotBlank
    private String phone;

    private String email;

}
