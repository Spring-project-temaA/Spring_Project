package com.example.smp_1.mapper;


import com.example.smp_1.dto.userDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface promiseHairMapper {
    userDto loginChk() throws Exception;
}
