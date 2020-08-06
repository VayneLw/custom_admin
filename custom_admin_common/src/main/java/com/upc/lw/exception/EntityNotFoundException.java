package com.upc.lw.exception;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:13
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz) {
        super(clazz.getName() + " not found data!");
    }
}
