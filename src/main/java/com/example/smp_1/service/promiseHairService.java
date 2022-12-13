package com.example.smp_1.service;


import com.example.smp_1.dto.userDto;

public interface promiseHairService {
    public void signUser(userDto userDto) throws Exception;

    //    아이디 중복체크
    public int checkUserId(String userId);

    //    전화번호 중복체크
    public int checkUserPh(String userPh);

    //    이메일 중복체크
    public int checkUserMail(String userMail);
}
