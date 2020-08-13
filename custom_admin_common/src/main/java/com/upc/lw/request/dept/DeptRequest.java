package com.upc.lw.request.dept;

import lombok.Data;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/13 17:30
 */
public interface DeptRequest {

    @Data
    class QueryArg{
        String pid;
    }
}
