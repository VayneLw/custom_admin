package com.upc.lw.annotation;

import javax.persistence.criteria.JoinType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description JPA多条件查询注解
 * @author: liwei
 * @date: 2020/8/11 13:59
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    /**
     * 对象得属性名
     * 1.标识本对象查询得字段名
     * 2.标识连接查询其他对象得字段名
     */
    String propName() default "";

    /**
     * 标识连接查询其他对象得字段名  比如User 连接查询Dept，使用字段名 "id" （Dept） 查询
     */
    String joinName() default "";

    /**
     *  连接查询得类型
     */
    JoinType joinType() default JoinType.LEFT;

    /**
     * 连接查询得字段名 比如User类中得 "dept" 字段名
     */
    Type type() default  Type.EQUAL;

    enum Type{
        IN,
        EQUAL,
        GREATER_THAN
    }

}
