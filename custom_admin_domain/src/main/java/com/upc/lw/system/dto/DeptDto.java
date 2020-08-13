package com.upc.lw.system.dto;

import com.upc.lw.base.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 14:49
 */
@Data
public class DeptDto extends BaseDto implements Serializable {
    private Long id;

    private String name;

    private Long parentId;
}
