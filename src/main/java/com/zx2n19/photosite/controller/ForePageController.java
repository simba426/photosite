package com.zx2n19.photosite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ForePageController {

    @GetMapping(value="/")
    public String index() {
        return "redirect:home";
    }

    @GetMapping(value="/home")
    public String home() {
        return "fore/home";
    }

    @GetMapping(value="/register")
    public String register(){
        return "fore/register";
    }

    @GetMapping(value="/login")
    public String login(){
        return "fore/login";
    }

    @GetMapping(value="/registerSuccess")
    public String registerSuccess(){
        return "fore/registerSuccess";
    }

    @GetMapping("/forelogout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:home";
    }

    @GetMapping("/photo")
    public String photo() {
        return "fore/photo";
    }
}
