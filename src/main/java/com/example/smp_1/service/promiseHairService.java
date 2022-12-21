package com.example.smp_1.service;


import com.example.smp_1.dto.apointDto;
import com.example.smp_1.dto.reviewDto;
import com.example.smp_1.dto.shopDto;
import com.example.smp_1.dto.userDto;

import java.util.List;

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

//    -------------------------------------------------
    //    유저 정보 수정
    public void updateUserInfo(userDto userDto);

//    유저 세션 갱신
    userDto changeSession(userDto userDto);

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

    //    ---------------- 리뷰 페이지 ---------------------

    //  shop  -> review 첫 페이지, 테이블 페이지
    public List<reviewDto> selectReviewDto() throws Exception;

    // reveiew 글쓰기 페이지
    public void insertReview(reviewDto reviewDto) throws Exception;

}
