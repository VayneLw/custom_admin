package com.upc.lw.system;

import com.upc.lw.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 角色表
 * @author: liwei
 * @date: 2020/8/6 14:21
 */
@Entity
@Table(name = "sys_role")
@Data
public class Role extends BaseEntity implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
