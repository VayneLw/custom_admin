package com.upc.lw.moudules.security.config.bean;

import lombok.Data;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 11:09
 */
@Data
public class SecurityProperties {
    private String header;

    private String tokenStartWith;

    private String base64Secret;

    private String onlineKey;

    private int expireMinutes;


    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }

}
