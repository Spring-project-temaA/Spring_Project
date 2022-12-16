package com.example.smp_1.service;


import com.example.smp_1.dto.apointDto;
import com.example.smp_1.dto.shopDto;
import com.example.smp_1.dto.userDto;

public interface promiseHairService {

//    ---------------- 유저 관련 ---------------------

    //    유저 회원가입
    public void signUser(userDto userDto) throws Exception;

    //    유저 아이디 중복체크
    public int checkUserId(String userId);

    //    유저 전화번호 중복체크
    public int checkUserPh(String userPh);

    //    유저 이메일 중복체크
    public int checkUserMail(String userMail);

    //    유저 로그인
    public userDto checkUserLogin(String userId, String userPw);

//    ---------------- 사업자 관련 ---------------------

    //    Owner 회원가입
    public void signOwner(shopDto shopDto) throws Exception;

    //    Shop 아이디 중복체크
    public int checkShopId(String shopId);

    //    가게번호 중복체크
    public int checkShopTel(String shopTel);

    //    OwnerPh 중복체크
    public int checkOwnerPh(String ownerPh);

    //    Owner 이메일 중복체크
    public int checkOwnerMail(String ownerMail);

    //    샵 로그인
    public shopDto checkShopLogin(String shopId, String shopPw);

    void insertAppointment(apointDto apointdto);
    
//    Shop Name 뿌리기
    String[] selectShopName();
}
