package com.upc.lw.system;

import com.upc.lw.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="pid")
    private Long parentId;

    /**
     * 状态 0失效  1 有效
     */
    private int status;
}
