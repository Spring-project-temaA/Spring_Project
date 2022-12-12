package com.example.smp_1.controller;


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
    private promiseHairService phservice;

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

//    중복체크를 위한 ajax 컨트롤러

    //    아이디 중복체크
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("userId") String id) {
        int cnt = phservice.checkId(id);
        return cnt;
    }

    //    전화번호 중복체크
    @PostMapping("/phCheck")
    @ResponseBody
    public int phCheck(@RequestParam("userPh") String ph) {
        int cnt = phservice.checkId(ph);
        return cnt;
    }

    //    이메일 중복체크
    @PostMapping("/mailCheck")
    @ResponseBody
    public int mailCheck(@RequestParam("userMail") String mail) {
        int cnt = phservice.checkId(mail);
        return cnt;
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUpUser";
    }

    @RequestMapping("/signUser")
    public String signUpUser(userDto userDto) throws Exception {
        phservice.signUser(userDto);
        return "main";
    }

    @RequestMapping("/signUpOwner")
    public String signUpOwner() {
        return "signUpOwner";
    }

    @RequestMapping("/signUpSelect")
    public String signUpSelect() {
        return "signUpSelect";
    }
}
