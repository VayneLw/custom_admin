package com.upc.lw.system.mapstruct;

import com.upc.lw.base.BaseMapper;
import com.upc.lw.system.Job;
import com.upc.lw.system.dto.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 14:52
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends BaseMapper<JobDto,Job> {
}
