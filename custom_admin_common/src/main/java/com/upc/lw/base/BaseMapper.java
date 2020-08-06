package com.upc.lw.base;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 17:54
 */
public interface BaseMapper<D,E> {

    /**
     * 转dto
     * @param d
     * @return
     */
    E toEntity(D d);

    /**
     * 转实体类
     * @param e
     * @return
     */
    D toDto(E e);
}
