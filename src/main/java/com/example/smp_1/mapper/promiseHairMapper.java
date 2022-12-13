package com.example.smp_1.mapper;


import com.example.smp_1.dto.userDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface promiseHairMapper {
    public void signUser(userDto userDto) throws Exception;

    //    유저 아이디 중복체크
    public int checkUserId(String userId);

    //    유저 전화번호 중복체크
    public int checkUserPh(String userPh);

    //    유저 이메일 중복체크
    public int checkUserMail(String userMail);
}