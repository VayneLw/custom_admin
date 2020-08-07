package com.upc.lw.jpa;

import com.alibaba.fastjson.JSONObject;
import com.upc.lw.system.User;
import com.upc.lw.system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/6 14:44
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void findAll(){
        List<User> userList = userRepository.findAll();
        log.info("===============ret:{}",JSONObject.toJSON(userList));
    }

    @Test
    public void save(){
        User user = new User();
        user.setPin("upc_lw_2020");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setNickName("lw_test_1");
        user.setPhone("admin");
        user.setCreateBy("lw");
        user.setCreateTime(new Date());
        User save = userRepository.save(user);
        log.info("============ret:{}",save);
    }

    /**
     * 测试update/delete时，需要添加 @Transactional
     */
    @Test
    public void findByPin(){
        User user = userRepository.findByPin("upc_lw_2018");
        log.info("===============ret:{}",JSONObject.toJSON(user));
    }

    @Test
    public void findByPins(){
        List<String> pins =  Lists.newArrayList("upc_lw_2018","upc_lw_2019");
        List<User> userList = userRepository.findByPinIn(pins);
        log.info("===============ret:{}",JSONObject.toJSON(userList));
    }

    @Test
    public void updatePassword(){
        userRepository.updatePassword("upc_lw_2018",passwordEncoder.encode("1234"));
    }

    @Test
    public void updateEmail(){
        userRepository.updateEmail("upc_lw_2018","lbj@163.com");
    }

    @Test
    public void findByEmail(){
        User user = userRepository.findByEmail("lbj@163.com");
        log.info("===============ret:{}",JSONObject.toJSON(user));
    }

}
