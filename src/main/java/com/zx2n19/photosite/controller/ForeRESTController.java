package com.zx2n19.photosite.controller;

import com.zx2n19.photosite.pojo.*;
import com.zx2n19.photosite.service.*;
import com.zx2n19.photosite.util.Result;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class ForeRESTController {
    @Autowired
    UserService userService;
    @Autowired
    PhotoService photoService;
    @Autowired
    CommentService commentService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderService orderService;

    @GetMapping("/forehome")
    public Object home() {
        return photoService.list();
    }

    @PostMapping("/forecreateOrder/{pid}")
    public Object createOrder(@RequestBody Order order, @PathVariable("pid") int pid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(null==user)
            return Result.fail("未登录");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUser(user);
        order.setStatus(OrderService.waitPay);
        order.setProduct(productService.get(pid));
        order.setNumber(1);
        orderService.add(order);
        Map<String,Object> map = new HashMap<>();
        map.put("oid", order.getId());
        map.put("total", order.getNumber() * order.getProduct().getPrice());

        return Result.success(map);

    }

    @GetMapping("/forepayed")
    public Object payed(int oid) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        orderService.update(order);
        return order;
    }

    @GetMapping("/foreproduct/{pid}")
    public Object product(@PathVariable("pid") int pid) {
        Product product = productService.get(pid);
        List<ProductImage> imgs = productImageService.listDetailProductImages(product);

        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        map.put("imgs", imgs);
        return Result.success(map);
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

    @GetMapping("/forepersonal/{uid}")
    public Object personal(@PathVariable("uid") int uid) {
        User user = userService.getById(uid);
        List<Photo> photos = photoService.listByUser(user);
        List<Product> products = productService.listByUser(user);
        Map<String, Object> map = new HashMap<>();
        map.put("photos", photos);
        map.put("products", products);
        map.put("user", user);

        return Result.success(map);
    }

    @PostMapping("/foreregister")
    public Object register(@RequestBody User user) {
        String username =  user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        boolean exist = userService.isExist(username);

        if(exist){
            String message ="用户名已经被使用,不能使用";
            return Result.fail(message);
        }

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";
        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.add(user);
        return Result.success();
    }

    @PostMapping("/forelogin")
    public Object login(@RequestBody User userParam, HttpSession session) {
        String username = userParam.getUsername();
        username = HtmlUtils.htmlEscape(username);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, userParam.getPassword());

        try {
            subject.login(token);
            User user = userService.getByUsername(username);
//          subject.getSession().setAttribute("user", user);
            session.setAttribute("user", user);
            return Result.success();
        } catch (AuthenticationException e) {
            String message ="账号密码错误";
            return Result.fail(message);
        }
    }

    @GetMapping("/forecheckLogin")
    public Object checkLogin( HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(null!=user)
            return Result.success();
        return Result.fail("未登录");
    }
}
