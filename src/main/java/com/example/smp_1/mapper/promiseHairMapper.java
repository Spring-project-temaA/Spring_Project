package com.example.smp_1.mapper;


import com.example.smp_1.dto.userDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface promiseHairMapper {
    public void signUser(userDto userDto) throws Exception;

    //    아이디 중복체크
    public int checkId(String userId);

    //    전화번호 중복체크
    public int checkPh(String userPh);

    //    이메일 중복체크
    public int checkMail(String userMail);
}