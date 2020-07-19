package com.zx2n19.photosite.controller;

import com.zx2n19.photosite.pojo.Comment;
import com.zx2n19.photosite.pojo.Photo;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.service.CommentService;
import com.zx2n19.photosite.service.PhotoService;
import com.zx2n19.photosite.service.UserService;
import com.zx2n19.photosite.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.*;


@RestController
public class ForeRESTController {
    @Autowired
    UserService userService;
    @Autowired
    PhotoService photoService;
    @Autowired
    CommentService commentService;

    @GetMapping("/forehome")
    public Object home() {
        return photoService.list();
    }

    @GetMapping("/forephoto/{pid}")
    public Object photo(@PathVariable("pid") int pid) {
        Photo photo = photoService.get(pid);

        List<Comment> comments = commentService.listByPhoto(photo);

        photoService.setCommentNumber(photo);

        Map<String,Object> map= new HashMap<>();
        map.put("photo", photo);
        map.put("comments", comments);

        return Result.success(map);
    }

    @PostMapping("/foreregister")
    public Object register(@RequestBody User user) {
        String username =  user.getUsername();
        String password = user.getPassword();
        System.out.println(username);
        System.out.println(password);
        int identity = user.getIdentity();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        boolean exist = userService.isExist(username);

        if(exist){
            String message ="用户名已经被使用,不能使用";
            return Result.fail(message);
        }

        user.setPassword(password);
        user.setIdentity(identity);

        userService.add(user);

        return Result.success();
    }

    @PostMapping("/forelogin")
    public Object login(@RequestBody User userParam, HttpSession session) {
        String username = userParam.getUsername();
        username = HtmlUtils.htmlEscape(username);
        String password = userParam.getPassword();

        User user = userService.get(username, password);
        if(null==user){
            String message ="账号密码错误";
            return Result.fail(message);
        }
        else{
            session.setAttribute("user", user);
            return Result.success();
        }
    }
}
