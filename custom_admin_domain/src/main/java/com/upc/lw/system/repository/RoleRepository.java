package com.upc.lw.system.repository;

import com.upc.lw.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 14:22
 */
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "select r.* from sys_user_roles u,sys_role r where u.user_id=?1  and u.role_id=r.id",nativeQuery = true)
    Set<Role> findByUserId(String userId);
}
