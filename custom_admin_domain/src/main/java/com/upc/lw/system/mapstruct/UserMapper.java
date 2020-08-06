package com.upc.lw.system.mapstruct;

import com.upc.lw.base.BaseMapper;
import com.upc.lw.system.User;
import com.upc.lw.system.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @Description
 * mapstruct 功能：
 *        1.实体类与DTO相互转换
 *        2.编译时会生成实现类
 * @author: liwei
 * @date: 2020/8/6 17:52
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto,User> {

}
