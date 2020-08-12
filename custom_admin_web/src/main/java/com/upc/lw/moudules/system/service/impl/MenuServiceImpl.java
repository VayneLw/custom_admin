package com.upc.lw.moudules.system.service.impl;

import com.google.common.collect.Lists;
import com.upc.lw.moudules.system.service.MenuService;
import com.upc.lw.moudules.system.service.RoleService;
import com.upc.lw.system.Menu;
import com.upc.lw.system.dto.MenuDto;
import com.upc.lw.system.dto.RoleDto;
import com.upc.lw.system.mapstruct.MenuMapper;
import com.upc.lw.system.repository.MenuRepository;
import com.upc.lw.system.vo.MenuMetaVo;
import com.upc.lw.system.vo.MenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 15:45
 */
@Slf4j
@SuppressWarnings("all")
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户id查询对应有权限得菜单列表
     * @param userId
     * @return
     */
    @Override
    public List<MenuDto> findMenusByUser(String userId) {
        List<MenuDto> ret = Lists.newArrayList();
        List<RoleDto> roleList = roleService.findByUserId(userId);
        if (!CollectionUtils.isEmpty(roleList)) {
            Set<Long> roleIds = roleList.stream()
                    .map(RoleDto::getId)
                    .collect(Collectors.toSet());

            if (!CollectionUtils.isEmpty(roleIds)) {
                Set<Menu> menuSet = menuRepository.findByRoleIds(roleIds);
                ret = menuSet.stream().map(menuMapper::toDto).collect(Collectors.toList());
            }
        }
        return ret;
    }

    /**
     * 构建树得层级目录关系
     * @param menus
     * @return
     */
    @Override
    public List<MenuDto> buildTree(List<MenuDto> menus) {
        List<MenuDto> tree = Lists.newArrayList();
        for(MenuDto parentMenu : menus){
            if(parentMenu.getParentId() == null){
                tree.add(parentMenu);
            }

            //找子目录
            for(MenuDto childMenu : menus){
                if(Objects.equals(childMenu.getParentId(), parentMenu.getId())){
                    if(CollectionUtils.isEmpty(parentMenu.getChildren())){
                        parentMenu.setChildren(Lists.newArrayList());
                    }
                    parentMenu.getChildren().add(childMenu);
                }
            }
        }
        return tree;
    }

    @Override
    public List<MenuVo> buildMenus(List<MenuDto> menus) {
        List<MenuVo> ret = Lists.newArrayList();
        menus.forEach(x->{
            MenuVo menuVo = MenuVo.builder()
                    .alwaysShow(true)
                    .component(x.getComponent())
                    .hidden(false)
                    .name(x.getTitle())
                    .path(x.getPath())
                    .meta(new MenuMetaVo(x.getTitle(), x.getIcon(), false))
                    .redirect("noredirect")
                    .build();
            if(!CollectionUtils.isEmpty(x.getChildren())){
                List<MenuVo> child = Lists.newArrayList();
                for(MenuDto menu: x.getChildren()){
                    MenuVo childMenuVo = MenuVo.builder()
                            .component(menu.getComponent())
                            .hidden(false)
                            .name(menu.getName())
                            .path(menu.getPath())
                            .meta(new MenuMetaVo(menu.getTitle(), menu.getIcon(), false))
                            .build();
                    child.add(childMenuVo);
                }
                menuVo.setChildren(child);
            }
            ret.add(menuVo);
        });
        return ret;
    }

}
