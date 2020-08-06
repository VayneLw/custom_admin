package com.upc.lw.response;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/5 16:24
 */
public class ResponseUtil {
    public static final String FAIL_CODE="100";
    public static final String PARAM_VALIDATE_CODE="101";

    public static Object paramFail(){
        Map<String,Object> ret = new HashMap<>(2);
        ret.put("code", PARAM_VALIDATE_CODE);
        ret.put("msg", "参数校验失败");
        return ret;
    }

    public static Object fail(){
        Map<String,Object> ret = new HashMap<>(2);
        ret.put("code", FAIL_CODE);
        ret.put("msg", "服务器繁忙，请稍后再试试");
        return ret;
    }
}
