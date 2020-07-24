package com.zx2n19.photosite.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

    @GetMapping(value="/forelogout")
    public String logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            subject.logout();
        return "redirect:home";
    }

    @GetMapping(value="/photo")
    public String photo() {
        return "fore/photo";
    }

    @GetMapping(value="/buy")
    public String buy() {
        return "fore/buy";
    }

    @GetMapping(value="/pay")
    public String pay(){
        return "fore/pay";
    }

    @GetMapping(value="/payed")
    public String payed(){
        return "fore/payed";
    }

    @GetMapping(value="/product")
    public String product() {
        return "fore/product";
    }

    @GetMapping(value="/personalpage")
    public String personal() {
        return "fore/personalpage";
    }
}
