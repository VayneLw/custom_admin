package com.upc.lw.exception;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/12 14:01
 */
public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {
        super("token not found!");
    }
}
