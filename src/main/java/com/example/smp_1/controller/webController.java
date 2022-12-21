package com.example.smp_1.controller;


import com.example.smp_1.dto.apointDto;
import com.example.smp_1.dto.reviewDto;
import com.example.smp_1.dto.shopDto;
import com.example.smp_1.dto.userDto;
import com.example.smp_1.service.promiseHairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
//로깅 위한 어노테이션, 프로젝트 완성 시 제거할 예정
//로깅 : 어플, 시스템이 동작하는 동안 시스템 동작정보를 시간순으로 기록하는 것을 의미 = 비 기능 요구사항.
//로그 보고 싶을 경우에는 log.info() 해서 로그 찍어서 보기. 디버깅 쉽다.
@Controller
public class webController {
    @Autowired
    private promiseHairService phService;


//    @RequestMapping(value = "/main", method = RequestMethod.GET)
//    public String main2() {
//        return "main";
//    }

    //    메인화면
    @RequestMapping("/main")
    public String main(Model model) {
        String[] shopName = phService.selectShopName();
        model.addAttribute("shopName", shopName);
        System.out.println(shopName);
        return "main";
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
        return "redirect:main";
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

    //    유저 마이페이지
    @RequestMapping("/userMypage")
    public String userMypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

//        바로 이 주소로 접근했을때의 처리
        if (session.getAttribute("user") == null) {
            model.addAttribute("message", "접근 권한이 없습니다.");
            return "myPageUser";
        }
        return "myPageUser";
    }


    //    유저 수정페이지로 이동
    @RequestMapping(value = "/myPageUserUpdate")
    public String updateUserPage() throws Exception {

        return "myPageUserUpdate";
    }

    //    유저 정보 수정
    @RequestMapping(value = "/userUpdate")
    public String userUpdate(userDto userDto, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        phService.updateUserInfo(userDto);
        if (session.getAttribute("user") != null) {
            userDto user = phService.changeSession(userDto);
            session.setAttribute("user", user);
        }
        return "redirect:userMypage";
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
        return "redirect:/main";
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

    //    Shop 마이페이지
    @RequestMapping("/shopMypage")
    public String shopMypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

//        바로 이 주소로 접근했을때의 처리
        if (session.getAttribute("shop") == null) {
            model.addAttribute("message", "접근 권한이 없습니다.");
            return "myPageShop";
        }
        return "myPageShop";
    }

    @RequestMapping("/signUpSelect")
    public String signUpSelect() {
        return "signUpSelect";
    }

    //    예약 화면
    @RequestMapping("/apoint")
    public String apoint(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();

//        바로 이 주소로 접근했을때의 처리
        if (session.getAttribute("user") == null) {
            model.addAttribute("message", "로그인 후 이용해주세요.");
            return "main";
        }
        return "appointment";
    }

    //    ---------------- 예약 관련 ---------------------
    @RequestMapping("/appointment")
    public String appointment() {
        return "appointment";
    }

    @RequestMapping("/insertAppointment")
    public String insertAppointment(apointDto apointdto) throws Exception {

        phService.insertAppointment(apointdto);
        return "redirect:main";
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
        return "redirect:main";
    }

    @RequestMapping("/apDesigner")
    public String apDesigned() {
        return "appointDesigner";
    }


    // 리뷰 페이지, 테이블
    @RequestMapping(value = "/shopReview", method = RequestMethod.GET)
    public ModelAndView openShopReview() throws Exception {
        List<reviewDto> reviewList = phService.selectReviewDto();
        ModelAndView mv = new ModelAndView("review/shopReviewTable");
        mv.addObject("reviewList", reviewList);
        return mv;
    }
    // 리뷰 글 작성 페이지
    @RequestMapping("/shopReviewWriter")
    public String insertReview() throws Exception {
        return"/review/shopReviewWriter";
    }

    @RequestMapping("/shopReviewIn")
    public String insertReviewWrite (reviewDto reviewDto) throws Exception {
        phService.insertReview(reviewDto);
        return "redirect:shopReview";
    }
}

