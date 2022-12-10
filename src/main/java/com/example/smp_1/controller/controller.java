package com.example.smp_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {

    @RequestMapping(value = "/signUpUser")
    public String signUp() {
        return "signUpUser";
    }

    @RequestMapping(value = "/signUpDS")
    public String owner() {
        return "signUpDesigner";
    }

    @RequestMapping(value = "/signUpSelect")
    public String select() {
        return "/signUpSelect";
    }

    @RequestMapping(value = "/appointment")
    public String appo() {
        return "/appointment";
    }

}

