package com.zx2n19.photosite.controller;

import com.zx2n19.photosite.pojo.Photo;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.service.PhotoService;
import com.zx2n19.photosite.service.UserService;
import com.zx2n19.photosite.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class PhotoController {
    @Autowired
    UserService userService;
    @Autowired
    PhotoService photoService;

    @GetMapping("/photos")
    public Page4Navigator<Photo> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, HttpSession session) throws Exception {
        start = start<0?0:start;
        User user = (User)session.getAttribute("user");
        int uid = user.getId();
        return photoService.list(uid, start, size,5 );
    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable("id") int id) throws Exception {
        return photoService.get(id);
    }

    @PostMapping("/photos")
    public Object add(@RequestBody Photo bean) throws Exception {
        bean.setCreateDate(new Date());
        photoService.add(bean);
        return bean;
    }

    @DeleteMapping("/photos/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
        photoService.delete(id);
        return null;
    }

    @PutMapping("/photos")
    public Object update(@RequestBody Photo bean) throws Exception {
        photoService.update(bean);
        return bean;
    }
}
