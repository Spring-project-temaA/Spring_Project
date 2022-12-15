package com.example.smp_1.controller;


import com.example.smp_1.dto.shopDto;
import com.example.smp_1.dto.userDto;
import com.example.smp_1.service.promiseHairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    //    유저 로그인
    @PostMapping("/userLogin")
    @ResponseBody
    public Object userLogin(@RequestParam("userId") String id, @RequestParam("userPw") String pw, HttpServletRequest request) {
        HttpSession session = request.getSession();

        userDto user = phService.checkUserLogin(id, pw);

        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        if (session.getAttribute("shop") != null) {
            session.removeAttribute("shop");
        }
        session.setAttribute("user", user);

        System.out.println(user);
        System.out.println(session.getAttribute("user"));

        if (user == null) {
            return 0;
        } else {
            return user;
        }
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

    //    Shop 로그인
    @PostMapping("/shopLogin")
    @ResponseBody
    public Object shopLogin(@RequestParam("shopId") String id, @RequestParam("shopPw") String pw, HttpServletRequest request) {

        HttpSession session = request.getSession();

        shopDto shop = phService.checkShopLogin(id, pw);

//        shop 세션이 이미 존재 할 경우 삭제하기 위함
        if (session.getAttribute("shop") != null) {
            session.removeAttribute("shop");
        }
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        session.setAttribute("shop", shop);

//        System.out.println(shop);
//        System.out.println(session.getAttribute("user"));
//        System.out.println(session.getAttribute("shop"));

        if (shop == null) {
            return 0;
        } else {
            return shop;
        }
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

//    로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.getAttribute("shop") != null) {
            session.removeAttribute("shop");
        }
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "main";
    }

}
