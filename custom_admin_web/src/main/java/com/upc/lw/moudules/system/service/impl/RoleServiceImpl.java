package com.upc.lw.moudules.system.service.impl;

import com.google.common.collect.Lists;
import com.upc.lw.moudules.system.service.RoleService;
import com.upc.lw.system.Role;
import com.upc.lw.system.dto.RoleDto;
import com.upc.lw.system.mapstruct.RoleMapper;
import com.upc.lw.system.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 15:54
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;


    /**
     * 根据用户id查询拥有得角色
     * @param userId
     * @return
     */
    @Override
    public List<RoleDto> findByUserId(String userId) {
        Set<Role> roles = roleRepository.findByUserId(userId);
        List<RoleDto> roleDtos = roleMapper.toDto(Lists.newArrayList(roles));
        return roleDtos;
    }
}
