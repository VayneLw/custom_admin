package com.upc.lw.utills;

import com.google.common.collect.Lists;
import com.upc.lw.annotation.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description Jpa动态查询字段处理
 * 重新接口类Specification 方法toPredicate
 * @author: liwei
 * @date: 2020/8/14 10:33
 */
@Slf4j
public class QueryHelp {

    /**
     * 重写动态查询条件转换
     *
     * @param root
     * @param query
     * @param criteriaBuilder
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Predicate toPredicate(Root<T> root, R query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = Lists.newArrayList();
        if (query == null) {
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        }

        try {
            List<Field> fieldList = getAllFields(query.getClass(), Lists.newArrayList());
            for (Field field : fieldList) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);

                Query fieldAnnotation = field.getAnnotation(Query.class);
                if (fieldAnnotation != null) {
                    String propName = fieldAnnotation.propName();
                    String joinName = fieldAnnotation.joinName();
                    JoinType joinType = fieldAnnotation.joinType();
                    Query.Type type = fieldAnnotation.type();

                    Class<?> fieldType = field.getType();
                    Object val = field.get(query);
                    if (Objects.isNull(val) || "".equals(val)) {
                        continue;
                    }

                    propName = StringUtils.isEmpty(propName) ? field.getName() : propName;

                    //连接查询类型
                    Join join = null;
                    if (!StringUtils.isEmpty(joinName)) {
                        join = root.join(joinName, joinType);
                    }

                    //补充查询条件
                    switch (type) {
                        case IN:
                            predicateList.add(getExpression(propName, join, root).in(val));
                            break;
                        case EQUAL:
                            predicateList.add(criteriaBuilder.equal(getExpression(propName, join, root)
                                    , val));
                            break;
                        case GREATER_THAN:
                            predicateList.add(criteriaBuilder.greaterThan(getExpression(propName, join, root)
                                    , (Comparable) val));
                            break;
                        default:
                            break;
                    }
                }

                field.setAccessible(accessible);
            }
        } catch (Exception e) {
            log.error("toPredicate error:", e);
        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }

    /**
     * 查询参数转换
     *
     * @param propName
     * @param join
     * @param root
     * @param <T>
     * @return
     */
    private static <T> Expression getExpression(String propName, Join join, Root<T> root) {
        if (join != null) {
            return join.get(propName);
        } else {
            return root.get(propName);
        }
    }

    /**
     * 获取class类下所有的字段
     *
     * @param clazz
     * @param fields
     * @return
     */
    private static List<Field> getAllFields(Class clazz, List<Field> fields) {
        if (CollectionUtils.isEmpty(fields)) {
            fields = Lists.newArrayList();
        }
        if (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            getAllFields(clazz.getSuperclass(), fields);
        }
        return fields;
    }

}
