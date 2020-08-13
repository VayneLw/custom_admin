package com.upc.lw.moudules.system.rest;

import com.upc.lw.moudules.system.service.MenuService;
import com.upc.lw.system.dto.MenuDto;
import com.upc.lw.system.vo.MenuVo;
import com.upc.lw.utills.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 菜单页--对外接口
 * @author: liwei
 * @date: 2020/8/11 13:59
 */
@Slf4j
@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 返回菜单页
     * @return
     */
    @RequestMapping("/build")
    public ResponseEntity<Object> buildMenus() {
        List<MenuDto> menuDtoList = menuService.findMenusByUser(SecurityUtils.getCurrentUserId());
        List<MenuDto> menuTree = menuService.buildTree(menuDtoList);
        List<MenuVo> menuVos = menuService.buildMenus(menuTree);
        return ResponseEntity.ok(menuVos);
    }
}
