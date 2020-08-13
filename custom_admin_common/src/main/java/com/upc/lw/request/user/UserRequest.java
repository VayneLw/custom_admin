package com.upc.lw.request.user;

import lombok.Data;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/13 17:55
 */
public interface UserRequest {
    @Data
    class QueryArg{
        String deptId;
    }
}
