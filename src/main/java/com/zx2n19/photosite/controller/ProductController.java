package com.zx2n19.photosite.controller;


import com.zx2n19.photosite.pojo.Product;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.service.ProductService;
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
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/products")
    public Page4Navigator<Product> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, HttpSession session) throws Exception {
        start = start<0?0:start;
        User user = (User)session.getAttribute("user");
        int uid = user.getId();
        return productService.listByUser(uid, start, size,5 );
    }

    @PostMapping("/products")
    public void add(Product bean, MultipartFile image, HttpServletRequest request, HttpSession session) throws IOException {
        User user = (User)session.getAttribute("user");
        //bean.setStock(Integer.parseInt(request.getParameter("stock")));
        //bean.setPrice(Double.parseDouble(request.getParameter("price")));
        bean.setUser(user);
        bean.setCreateDate(new Date());
        productService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
    }

    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id")int id) {
        return productService.get(id);
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id")int id, HttpServletRequest request) {
        productService.delete(id);
        File imageFolder= new File(request.getServletContext().getRealPath("img/productSingle"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    @PutMapping("/products")
    public void update(@RequestBody Product bean) {
        productService.update(bean);
    }

    public void saveOrUpdateImageFile(Product bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/productSingle"));
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

}
