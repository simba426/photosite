package com.zx2n19.photosite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping(value="/admin")
    public String admin(){
        return "redirect:photo_list";
    }

    @GetMapping(value="/user_list")
    public String index() {
        return "admin/listUser";
    }

    @GetMapping(value="/user_comment_list")
    public String listUserComment() {
        return "admin/listUserComment";
    }

    @GetMapping(value="/photo_comment_list")
    public String listPhotoComment() {
        return "admin/listPhotoComment";
    }


    @GetMapping(value="/photo_list")
    public String listPhoto() {
        return "admin/listPhoto";
    }

    @GetMapping(value="/photo_edit")
    public String editPhoto() {
        return "admin/editPhoto";
    }


}
