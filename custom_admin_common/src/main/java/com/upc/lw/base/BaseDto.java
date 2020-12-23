package com.upc.lw.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:24
 */
@Data
public class BaseDto implements Serializable {
    private String createBy;

    private Date createTime;
}
