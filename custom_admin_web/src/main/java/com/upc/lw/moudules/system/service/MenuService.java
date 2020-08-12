package com.upc.lw.moudules.system.service;

import com.upc.lw.system.dto.MenuDto;
import com.upc.lw.system.vo.MenuVo;

import java.util.List;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 15:45
 */
public interface MenuService {

    List<MenuDto> findMenusByUser(String userId);

    List<MenuDto> buildTree(List<MenuDto> menus);

    List<MenuVo> buildMenus(List<MenuDto> menus);
}
