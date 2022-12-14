package com.example.smp_1.controller;


import com.example.smp_1.dto.shopDto;
import com.example.smp_1.dto.userDto;
import com.example.smp_1.service.promiseHairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class webController {
    @Autowired
    private promiseHairService phService;

    //    메인화면
    @RequestMapping("/main")

    public String main() {
        return "main";
    }

    //    캘린더 api 테스트
    @RequestMapping("/test")
    public String test() {
        return "fullcalTest";
    }


//    ---------------- 유저 관련 ---------------------

    //    유저 회원가입 창으로 가기
    @RequestMapping("/signUpUser")
    public String signUpUser() {
        return "signUpUser";
    }

//    중복체크를 위한 ajax 컨트롤러

    //    유저 아이디 중복체크
    @PostMapping("/userIdCheck")
    @ResponseBody
    public int userIdCheck(@RequestParam("userId") String id) {
        int cnt = phService.checkUserId(id);
        return cnt;
    }

    //    유저 전화번호 중복체크
    @PostMapping("/userPhCheck")
    @ResponseBody
    public int userPhCheck(@RequestParam("userPh") String ph) {
        int cnt = phService.checkUserPh(ph);
        return cnt;
    }

    //    유저 이메일 중복체크
    @PostMapping("/userMailCheck")
    @ResponseBody
    public int userMailCheck(@RequestParam("userMail") String mail) {
        int cnt = phService.checkUserMail(mail);
        return cnt;
    }

    //    유저 회원가입
    @RequestMapping("/signUser")
    public String insertUser(userDto userDto) throws Exception {
        phService.signUser(userDto);
        return "main";
    }


//    ---------------- 사업자 관련 ---------------------

    //    사업자 회원가입 창으로 가기
    @RequestMapping("/signUpOwner")
    public String signUpOwner() {
        return "signUpOwner";
    }

//    중복체크를 위한 ajax 컨트롤러

    //    Shop 아이디 중복체크
    @PostMapping("/shopIdCheck")
    @ResponseBody
    public int shopIdCheck(@RequestParam("shopId") String id) {
        int cnt = phService.checkShopId(id);
        return cnt;
    }

    //    Shop 번호 중복체크
    @PostMapping("/shopTelCheck")
    @ResponseBody
    public int shopTelCheck(@RequestParam("shopTel") String tel) {
        int cnt = phService.checkShopTel(tel);
        return cnt;
    }

    //    Owner 번호 중복체크
    @PostMapping("/ownerPhCheck")
    @ResponseBody
    public int ownerPhCheck(@RequestParam("ownerPh") String ph) {
        int cnt = phService.checkOwnerPh(ph);
        return cnt;
    }

    //    Owner 이메일 중복체크
    @PostMapping("/ownerMailCheck")
    @ResponseBody
    public int ownerMailCheck(@RequestParam("ownerMail") String mail) {
        int cnt = phService.checkOwnerMail(mail);
        return cnt;
    }

    //    Owner 회원가입
    @RequestMapping("/signOwner")
    public String insertOwner(shopDto shopDto) throws Exception {
        phService.signOwner(shopDto);
        return "main";
    }

    @RequestMapping("/signUpSelect")
    public String signUpSelect() {
        return "signUpSelect";
    }

    //    예약 화면
    @RequestMapping("/apoint")
    public String apoint() {
        return "appointment";
    }

}
