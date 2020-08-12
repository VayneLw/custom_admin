package com.upc.lw.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.lw.base.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 15:42
 */
@Data
public class MenuDto extends BaseDto implements Serializable {

    @JsonIgnore
    private Long id;

    private List<MenuDto> children;

    private Long parentId;

    private String name;

    private String component;

    private String path;

    private String title;

    private String icon;
}
