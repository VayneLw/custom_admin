package com.upc.lw.system.repository;

import com.upc.lw.system.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 14:21
 */
public interface DeptRepository extends JpaRepository<Dept, String> {

    @Query(value = "select * from sys_dept where pid=? and status=1", nativeQuery = true)
    List<Dept> findByPid(Integer pid);

    List<Dept> findByPidIsNull();
}
