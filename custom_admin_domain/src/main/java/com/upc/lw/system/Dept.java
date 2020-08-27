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
 * @Description
 * @author: liwei
 * @date: 2020/8/7 10:57
 */
@Data
@Entity
@Table(name = "sys_dept")
public class Dept extends BaseEntity implements Serializable {

    @Id
    @Column(name="dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="pid")
    private Long pid;

    private Integer subCount=0;

}
