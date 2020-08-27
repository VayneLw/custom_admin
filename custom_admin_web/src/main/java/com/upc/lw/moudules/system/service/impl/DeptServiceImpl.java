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
import org.springframework.util.CollectionUtils;

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
    public List<DeptDto> findDeptList(DeptRequest.QueryArg arg) {
        List<Dept> deptList;
        if (StringUtils.isNotEmpty(arg.getPid())) {
            deptList = deptRepository.findByPid(Integer.valueOf(arg.getPid()));
        } else {
            deptList = deptRepository.findByPidIsNull();
        }
        List<DeptDto> deptDtoList = deptMapper.toDto(deptList);
        return deptDtoList;
    }

    @Override
    public Map<String, Object> buildTrees(List<DeptDto> deptList) {
        if(CollectionUtils.isEmpty(deptList)){
            PageUtils.toPage(null, 0);
        }

        //遍历查询子节点;暂只处理两级部门
        for(DeptDto dept : deptList){
            if(dept.getHasChildren()){
                List<Dept> childDept = deptRepository.findByPid(dept.getId().intValue());
                List<DeptDto> deptDtoList = deptMapper.toDto(childDept);
                dept.setChildren(deptDtoList);
            }
        }

        Map<String, Object> page = PageUtils.toPage(deptList, deptList.size());
        return page;
    }

}
