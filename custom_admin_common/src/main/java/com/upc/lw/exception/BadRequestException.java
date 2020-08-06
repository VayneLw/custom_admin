package com.upc.lw.exception;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:19
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }
}
