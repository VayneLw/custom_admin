package com.upc.lw.utills;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/13 15:47
 */
public class PageUtils {
    public static Map<String, Object> toPage(Page page) {
        Map<String, Object> ret = new HashMap<>(2);
        ret.put("content", page.getContent());
        ret.put("totalElements", page.getTotalElements());

        return ret;
    }

    public static Map<String, Object> toPage(Object content, int totalElements) {
        Map<String, Object> ret = new HashMap<>(2);
        ret.put("content", content);
        ret.put("totalElements", totalElements);

        return ret;
    }
}
