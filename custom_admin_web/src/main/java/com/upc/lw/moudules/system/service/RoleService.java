package com.upc.lw.moudules.system.service;

import com.upc.lw.system.dto.RoleDto;

import java.util.List;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 15:54
 */
public interface RoleService {
    List<RoleDto> findByUserId(String userId);
}
