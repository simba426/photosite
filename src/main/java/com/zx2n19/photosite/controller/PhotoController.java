package com.zx2n19.photosite.controller;

import com.zx2n19.photosite.pojo.Photo;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.service.CommentService;
import com.zx2n19.photosite.service.LikedService;
import com.zx2n19.photosite.service.PhotoService;
import com.zx2n19.photosite.service.UserService;
import com.zx2n19.photosite.util.ImageUtil;
import com.zx2n19.photosite.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class PhotoController {
    @Autowired
    UserService userService;
    @Autowired
    PhotoService photoService;
    @Autowired
    CommentService commentService;
    @Autowired
    LikedService likedService;

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
    public void add(Photo bean, MultipartFile image, HttpServletRequest request, HttpSession session) throws Exception {
        User user = (User)session.getAttribute("user");
        bean.setUser(user);
        bean.setCreateDate(new Date());
        photoService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
    }

    public void saveOrUpdateImageFile(Photo bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/photo"));
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

    @DeleteMapping("/photos/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception {
        photoService.delete(id);
        File imageFolder= new File(request.getServletContext().getRealPath("img/photo"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    @PutMapping("/photos/{id}")
    public Object update(Photo bean, MultipartFile image,HttpServletRequest request, HttpSession session) throws Exception {
        bean.setName(request.getParameter("name"));
        bean.setCategory(request.getParameter("category"));
        bean.setDescription(request.getParameter("description"));
        bean.setAperture(request.getParameter("aperture"));
        bean.setShutterSpeed(request.getParameter("shutterSpeed"));
        bean.setIso(request.getParameter("iso"));
        bean.setCamera(request.getParameter("camera"));
        bean.setCreateDate(new Date());
        bean.setUser((User)session.getAttribute("user"));
        photoService.update(bean);
        if(image!=null) {
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }
}
