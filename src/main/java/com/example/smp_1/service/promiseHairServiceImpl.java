package com.example.smp_1.service;

import com.example.smp_1.dto.apointDto;
import com.example.smp_1.dto.shopDto;
import com.example.smp_1.dto.userDto;
import com.example.smp_1.mapper.promiseHairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class promiseHairServiceImpl implements promiseHairService {
    @Autowired
    private promiseHairMapper phMapper;


    //    유저 회원가입
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

    //    유저 로그인
    @Override
    public userDto checkUserLogin(String id, String pw) {
        userDto userdto = phMapper.checkUserLogin(id, pw);
        return userdto;
    }

    //    Owner 회원가입
    @Override
    public void signOwner(shopDto shopDto) throws Exception {
        phMapper.signOwner(shopDto);
    }

    //    Shop 아이디 중복체크
    @Override
    public int checkShopId(String shopId) {
        return 0;
    }

    //    가게번호 중복체크
    @Override
    public int checkShopTel(String shopTel) {
        return 0;
    }

    //    OwnerPh 중복체크
    @Override
    public int checkOwnerPh(String ownerPh) {
        return 0;
    }

    //    Owner 이메일 중복체크
    @Override
    public int checkOwnerMail(String ownerMail) {
        return 0;
    }

    //    Shop 로그인
    @Override
    public shopDto checkShopLogin(String userId, String userPw) {
        shopDto shopdto = phMapper.checkShopLogin(userId, userPw);
        return shopdto;
    }

    @Override
    public void insertAppointment(apointDto apointdto) {
        phMapper.insertAppointment(apointdto);
    }

//    shopName 뿌리기
    @Override
    public String[] selectShopName() {
        String[] shopName = phMapper.selectShopName();
        return shopName;
    }
}
