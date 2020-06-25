package com.zx2n19.photosite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    @GetMapping(value="/admin")
    public String admin(){
        return "redirect:admin_index";
    }
    @GetMapping(value="/admin_index")
    public String index() {
        return "admin/index";
    }
    @GetMapping(value="/login")
    public String login() {
        return "admin/login";
    }
}
