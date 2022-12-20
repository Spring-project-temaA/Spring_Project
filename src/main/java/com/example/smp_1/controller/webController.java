package com.example.smp_1.controller;


import com.example.smp_1.dto.apointDto;
import com.example.smp_1.dto.shopDto;
import com.example.smp_1.dto.userDto;
import com.example.smp_1.service.emailService;
import com.example.smp_1.service.promiseHairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class webController {
    @Autowired
    private promiseHairService phService;

    @Autowired
    private emailService emailService;


    //    유저 이메일 인증
    @PostMapping("/userEmailSend")
    @ResponseBody
//    ajax를 통해서 값을 받고 거기로 이메일을 보냄
    public String userEmailSend(@RequestParam("userMail") String email) throws Exception {

        String data = emailService.sendSimpleMessage(email);

//        ajax로 값을 보내줘서 비교하게 함
        return data;
    }

    //    Shop 이메일 인증
    @PostMapping("/ownerEmailSend")
    @ResponseBody
//    ajax를 통해서 값을 받고 거기로 이메일을 보냄
    public String ownerEmailSend(@RequestParam("ownerMail") String email) throws Exception {

        String data = emailService.sendSimpleMessage(email);

//        ajax로 값을 보내줘서 비교하게 함
        return data;
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    //    메인화면
    @RequestMapping("/main")
    public String main(Model model) {
        String[] shopName = phService.selectShopName();
//        Shop 버튼 눌렀을때 화면에 ShopName 뿌리기 위함
        model.addAttribute("shopName", shopName);
//        System.out.println(shopName);
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
    public Object userLogin(@RequestParam("userId") String id, @RequestParam("userPw") String pw,@RequestParam("apointUserId")String apointId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        userDto user = phService.checkUserLogin(id, pw);

        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        if (session.getAttribute("shop") != null) {
            session.removeAttribute("shop");
        }
        session.setAttribute("user", user);

        if (user == null) {
            return 0;
        } else {
            apointDto apointDto = phService.apointCheck(apointId);
            session.setAttribute("apoint", apointDto);
            System.out.println(session.getAttribute("apoint"));
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

    //    예약목록 가져오기
    @PostMapping ("/getApoints")
    @ResponseBody
    public Object getApoints(@RequestParam("apointUserId") String id, HttpServletRequest request) {
        HttpSession session = request.getSession();

        List<apointDto> apointDto = phService.getApoints(id);
        session.setAttribute("apoints", apointDto);
        System.out.println(session.getAttribute("apoints"));

        return apointDto;
    }
}
