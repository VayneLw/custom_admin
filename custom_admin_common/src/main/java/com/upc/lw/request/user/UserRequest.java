package com.upc.lw.request.user;

import com.upc.lw.annotation.Query;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/13 17:55
 */
public interface UserRequest {
    @Data
    class QueryArg{
        @Query(propName = "id",type = Query.Type.EQUAL,joinName = "dept")
        String deptId;

        @Query(type = Query.Type.GREATER_THAN)
        Date createTime;

        @Query
        String id;
    }
}
