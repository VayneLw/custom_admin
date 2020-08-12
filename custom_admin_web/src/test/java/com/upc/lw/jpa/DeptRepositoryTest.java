package com.upc.lw.jpa;

import com.upc.lw.system.Dept;
import com.upc.lw.system.repository.DeptRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 14:42
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptRepositoryTest {

    @Autowired
    private DeptRepository deptRepository;


    @Test
    public void saveDept(){
        Dept dept = new Dept();
        dept.setName("研发部");
        dept.setStatus(1);

        Dept save = deptRepository.save(dept);
        log.info("============ret:{}",save);
    }
}
