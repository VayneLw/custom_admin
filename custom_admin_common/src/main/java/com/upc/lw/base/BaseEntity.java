package com.upc.lw.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 实体类通用字段
 * @author: liwei
 * @date: 2020/8/6 14:24
 */
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @Column(name="create_by",updatable = false)
    private String createBy;

    @Column(name="create_time")
    private Date createTime;
}
