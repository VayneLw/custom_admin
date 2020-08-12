package com.upc.lw.system.repository;

import com.upc.lw.system.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 15:44
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = "select m.* from sys_menus_roles r,sys_menu m where r.menu_id=m.menu_id and r.role_id in ?1",nativeQuery = true)
    Set<Menu> findByRoleIds(Set<Long> roleIds);
}
