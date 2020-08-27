package com.upc.lw.system.dto;

import com.upc.lw.base.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 14:49
 */
@Data
public class DeptDto extends BaseDto implements Serializable {
    private Long id;

    private String name;

    private Long pid;

    private Integer subCount = 0;

    private List<DeptDto> children;

    public boolean getHasChildren() {
        return subCount > 0;
    }

    public String getLabel(){
        return name;
    }
}
