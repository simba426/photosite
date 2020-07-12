package com.zx2n19.photosite.controller;


import com.zx2n19.photosite.pojo.Product;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.service.ProductService;
import com.zx2n19.photosite.service.UserService;
import com.zx2n19.photosite.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public void add(@RequestBody Product bean, HttpSession session) {
        User user = (User)session.getAttribute("user");
        bean.setUser(user);
        bean.setCreateDate(new Date());
        productService.update(bean);
    }

    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id")int id) {
        return productService.get(id);
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id")int id) {
        productService.delete(id);
        return null;
    }

    @PutMapping("/products")
    public void update(@RequestBody Product bean) {
        productService.update(bean);
    }

}
