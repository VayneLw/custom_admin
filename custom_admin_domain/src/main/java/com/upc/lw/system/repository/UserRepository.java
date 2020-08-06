package com.upc.lw.system.repository;

import com.upc.lw.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description JPA功能：
 * https://www.jianshu.com/p/c23c82a8fcfc
 * 1.通过解析方法名创建查询，框架在进行方法名解析时，会先把方法名多余的前缀find…By, read…By, query…By, count…By以及get…By截取掉，
 * 然后对剩下部分进行解析，第一个By会被用作分隔符来指示实际查询条件的开始
 * <p>
 * 2. @Query有nativeQuery=true，表示可执行的原生sql，原生sql指可以直接复制sql语句给参数赋值就能运行
 *   @Query无nativeQuery=true， 表示不是原生sql，查询语句中的表名则是对应的项目中实体类的类名
 *
 * 3.查询占位符
 *   （1.）指定位置参数 eg.  ? 1
 *   （2.）执行参数名  @Param("email)   :email
 *
 * 4.使用update/delete 方法时，需要有注解 @Transactional
 *
 * @author: liwei
 * @date: 2020/8/6 14:44
 */
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * 根据pin查询用户信息
     *
     * @param pin
     * @return
     */
    User findByPin(String pin);

    /**
     * 查询用户
     * @param pinList
     * @return
     */
    List<User> findByPinIn(List<String> pinList);

    /**
     * 根据邮箱查询
     * @param email
     * @return
     */
    @Query(value = "select * from sys_user where email=:email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    @Query(value = "select * from sys_user where phone=:phone", nativeQuery = true)
    User findByPhone(@Param("phone") String phone);

    /**
     * 更新密码
     *
     * @param pin      用户pin
     * @param password 密码
     */
    @Modifying
    @Query(value = "update sys_user set  password= ?2 where pin= ?1", nativeQuery = true)
    void updatePassword(String pin, String password);

    /**
     * 更新邮箱
     *
     * @param pin
     * @param email
     */
    @Modifying
    @Query(value = "update User set  email=?2 where pin=?1")
    void updateEmail(String pin, String email);

}
