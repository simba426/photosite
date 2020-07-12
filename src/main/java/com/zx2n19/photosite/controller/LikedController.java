package com.zx2n19.photosite.controller;


import com.zx2n19.photosite.pojo.Liked;
import com.zx2n19.photosite.pojo.Photo;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.service.LikedService;
import com.zx2n19.photosite.service.PhotoService;
import com.zx2n19.photosite.util.Page4Navigator;
import com.zx2n19.photosite.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class LikedController {

    @Autowired
    LikedService likedService;

    @GetMapping("/likeds")
    public Page4Navigator<Liked> listByUser(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, HttpSession session) {
        start = start<0?0:start;
        User user = (User)session.getAttribute("user");
        int uid = user.getId();
        return likedService.listUserLiked(uid, start, size,5 );
    }

    @GetMapping("/likeds/{id}")
    public Liked get(@PathVariable("id")int id) {
        return likedService.get(id);
    }

    @PostMapping("/likeds")
    public Object add(@RequestBody Liked bean, HttpSession session) {
        User user = (User)session.getAttribute("user");
        Photo photo = bean.getPhoto();
        boolean exist = likedService.isExist(user, photo);

        if(exist){
            String message ="已经添加了该照片";
            return Result.fail(message);
        }

        bean.setUser(user);

        likedService.add(bean);
        return Result.success();
    }

    @DeleteMapping("likeds/{id}")
    public String delete(@PathVariable("id") int id) {
        likedService.delete(id);
        return null;
    }


}
