package com.upc.lw.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 16:41
 */
@Data
@AllArgsConstructor
public class MenuMetaVo {
    private String title;

    private String icon;

    private Boolean noCache;
}
