package com.upc.lw.utills;

import com.alibaba.fastjson.JSONObject;
import com.upc.lw.exception.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Description 每次请求，获取当前上下文用户信息
 * @author: liwei
 * @date: 2020/8/11 17:15
 */
@Component
public class SecurityUtils {

    public static UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            throw new BadRequestException("登录信息为空");
        } else {
            UserDetails ret = (UserDetails) principal;
            return ret;
        }
    }

    public static String getCurrentUserId() {
        UserDetails currentUser = getCurrentUser();
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(currentUser);
        JSONObject user = (JSONObject) JSONObject.toJSON(jsonObject.get("user"));
        return (String) user.get("id");
    }

}
