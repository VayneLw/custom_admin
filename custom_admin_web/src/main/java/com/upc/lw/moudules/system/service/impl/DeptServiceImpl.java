package com.upc.lw.moudules.system.service.impl;

import com.upc.lw.moudules.system.service.DeptService;
import com.upc.lw.request.dept.DeptRequest;
import com.upc.lw.system.Dept;
import com.upc.lw.system.dto.DeptDto;
import com.upc.lw.system.mapstruct.DeptMapper;
import com.upc.lw.system.repository.DeptRepository;
import com.upc.lw.utills.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/13 17:08
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Map<String, Object> findDeptList(DeptRequest.QueryArg arg) {
        List<Dept> deptList;
        if (StringUtils.isNotEmpty(arg.getPid())) {
            deptList = deptRepository.findByParentId(Integer.valueOf(arg.getPid()));
        } else {
            deptList = deptRepository.findByParentIdIsNull();
        }
        List<DeptDto> deptDtoList = deptMapper.toDto(deptList);
        Map<String, Object> page = PageUtils.toPage(deptDtoList, deptDtoList.size());
        return page;
    }
}
