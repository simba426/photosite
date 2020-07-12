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

    @GetMapping(value="/product_list")
    public String listProduct() {
        return "admin/listProduct";
    }

    @GetMapping(value="/product_edit")
    public String editProduct() {
        return "admin/editProduct";
    }

    @GetMapping(value="/product_img_list")
    public String listProductImage() {
        return "admin/listProductImage";
    }

    @GetMapping(value="/user_comment_list")
    public String listUserComment() {
        return "admin/listUserComment";
    }

    @GetMapping(value="/photo_comment_list")
    public String listPhotoComment() {
        return "admin/listPhotoComment";
    }

    @GetMapping(value="/liked_list")
    public String listLiked() {
        return "admin/listLiked";
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
