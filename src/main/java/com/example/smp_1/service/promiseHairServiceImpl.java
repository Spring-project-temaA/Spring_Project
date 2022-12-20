package com.example.smp_1.service;

import com.example.smp_1.dto.apointDto;
import com.example.smp_1.dto.shopDto;
import com.example.smp_1.dto.userDto;
import com.example.smp_1.mapper.promiseHairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 유저 마이페이지 수정
    public void updateUserInfo(userDto userDto) {
        phMapper.updateUserInfo(userDto);
    }

    //    세션 최신화
    @Override
    public userDto changeUserSession(userDto userDto) {
        return phMapper.changeUserSession(userDto);
    }
    
//    Shop 마이페이지 수정
    @Override
    public void updateShopInfo(shopDto shopDto) {
        phMapper.updateShopInfo(shopDto);
    }

    @Override
    public shopDto changeShopSession(shopDto shopDto) {
        return phMapper.changeShopSession(shopDto);
    }

    //    Owner 회원가입
    @Override
    public void signOwner(shopDto shopDto) throws Exception {
        phMapper.signOwner(shopDto);
    }

    //    Shop 아이디 중복체크
    @Override
    public int checkShopId(String shopId) {
        int cnt = phMapper.checkShopId(shopId);
        return cnt;
    }

    //    가게번호 중복체크
    @Override
    public int checkShopTel(String shopTel) {
        int cnt = phMapper.checkShopTel(shopTel);
        return cnt;
    }

    //    OwnerPh 중복체크
    @Override
    public int checkOwnerPh(String ownerPh) {
        int cnt = phMapper.checkOwnerPh(ownerPh);
        return cnt;
    }

    //    Owner 이메일 중복체크
    @Override
    public int checkOwnerMail(String ownerMail) {
        int cnt = phMapper.checkOwnerMail(ownerMail);
        return cnt;
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

    //    예약 확인
    @Override
    public apointDto apointCheck(String apointUserId) {
        return phMapper.apointCheck(apointUserId);
    }


    //    모든 예약 가져오기
    @Override
    public List<apointDto> getApoints(String apointUserId) {
        return phMapper.getApoints(apointUserId);
    }


}
