package com.zx2n19.photosite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping(value="/admin")
    public String admin(){
        return "redirect:user_list";
    }

    @GetMapping(value="/user_list")
    public String index() {
        return "admin/listUser";
    }

    @GetMapping(value="/photo_list")
    public String photos() {
        return "admin/listPhoto";
    }


}
