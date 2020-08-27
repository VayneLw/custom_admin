package com.upc.lw.moudules.system.service;

import com.upc.lw.request.dept.DeptRequest;
import com.upc.lw.system.dto.DeptDto;

import java.util.List;
import java.util.Map;

/**
 * @Description 部门service类
 * @author: liwei
 * @date: 2020/8/13 17:08
 */
public interface DeptService {
    List<DeptDto> findDeptList(DeptRequest.QueryArg arg);

    Map<String, Object> buildTrees(List<DeptDto> deptList);
}
