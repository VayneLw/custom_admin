package com.upc.lw.system.repository;

import com.upc.lw.system.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 14:21
 */
public interface DeptRepository extends JpaRepository<Dept, String> {

}
