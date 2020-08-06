package com.upc.lw.moudules.security.config.bean;

import lombok.Data;

/**
 * @Description  验证码信息
 * @author: liwei
 * @date: 2020/8/5 12:33
 */
@Data
public class LoginCode {
    /**
     *验证码类型
     */
    private LoginCodeEnum codeType;

    /**
     *验证码过期时间
     */
    private long expiration;

    /**
     *验证码长度
     */
    private int length;

    /**
     *验证码宽度
     */
    private int width;

    /**
     *验证码高度
     */
    private int height;

    /**
     *验证码字体
     */
    private String frontName;

    /**
     *验证码字体大小
     */
    private int frontSize;
}
