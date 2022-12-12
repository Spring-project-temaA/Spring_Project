package com.example.smp_1.controller;


import com.example.smp_1.service.promiseHairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUpUser";
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
