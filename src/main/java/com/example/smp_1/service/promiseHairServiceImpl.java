package com.example.smp_1.service;

import com.example.smp_1.dto.userDto;
import com.example.smp_1.mapper.promiseHairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class promiseHairServiceImpl implements promiseHairService {
    @Autowired
    private promiseHairMapper phMapper;


    @Override
    public void signUser(userDto userDto) throws Exception {
        phMapper.signUser(userDto);
    }

    //    유저 아이디 중복체크
    @Override
    public int checkUserId(String userId) {
        int cnt = phMapper.checkUserId(userId);
//        System.out.println(cnt);
        return cnt;
    }

    //    유저 전화번호 중복체크
    @Override
    public int checkUserPh(String userPh) {
        int cnt = phMapper.checkUserPh(userPh);
//        System.out.println(cnt);
        return cnt;
    }

    //    유저 이메일 중복체크
    @Override
    public int checkUserMail(String userMail) {
        int cnt = phMapper.checkUserMail(userMail);
//        System.out.println(cnt);
        return cnt;
    }
}
