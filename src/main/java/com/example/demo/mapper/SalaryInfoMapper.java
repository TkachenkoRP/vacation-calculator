package com.example.demo.mapper;

import com.example.demo.dto.SalaryInfoDto;
import com.example.demo.model.SalaryInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalaryInfoMapper {
    SalaryInfo toEntity(SalaryInfoDto request);
}
