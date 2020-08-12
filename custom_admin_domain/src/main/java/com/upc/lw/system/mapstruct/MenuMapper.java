package com.upc.lw.system.mapstruct;

import com.upc.lw.base.BaseMapper;
import com.upc.lw.system.Menu;
import com.upc.lw.system.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 15:44
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper  extends BaseMapper<MenuDto,Menu> {
}
