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

    //    아이디 중복체크
    @Override
    public int checkId(String userId) {
        int cnt = phMapper.checkId(userId);
//        System.out.println(cnt);
        return cnt;
    }

    //    전화번호 중복체크
    @Override
    public int checkPh(String userPh) {
        int cnt = phMapper.checkId(userPh);
//        System.out.println(cnt);
        return cnt;
    }

    //    이메일 중복체크
    @Override
    public int checkMail(String userMail) {
        int cnt = phMapper.checkId(userMail);
//        System.out.println(cnt);
        return cnt;
    }
}
