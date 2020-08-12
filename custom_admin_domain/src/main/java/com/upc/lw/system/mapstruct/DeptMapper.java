package com.upc.lw.system.mapstruct;

import com.upc.lw.base.BaseMapper;
import com.upc.lw.system.Dept;
import com.upc.lw.system.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 14:50
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto,Dept> {

}
