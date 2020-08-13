package com.upc.lw.moudules.system.service;

import com.upc.lw.request.dept.DeptRequest;

import java.util.Map;

/**
 * @Description 部门service类
 * @author: liwei
 * @date: 2020/8/13 17:08
 */
public interface DeptService {
    Map<String,Object> findDeptList(DeptRequest.QueryArg arg);
}
