package com.zx2n19.photosite.controller;

import com.zx2n19.photosite.pojo.Order;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.service.OrderService;
import com.zx2n19.photosite.util.Page4Navigator;
import com.zx2n19.photosite.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/createdOrders")
    public Page4Navigator<Order> listByCid(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, HttpSession session) {
        start = start<0?0:start;
        User user = (User)session.getAttribute("user");
        return orderService.listByCid(user, start, size, 5);
    }

    @GetMapping("/receivedOrders")
    public Page4Navigator<Order> listBySid(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, HttpSession session) {
        start = start<0?0:start;
        User user = (User)session.getAttribute("user");
        return orderService.listBySid(user.getId(), start, size, 5);
    }

    @PutMapping("deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable int oid) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return Result.success();
    }

    @PutMapping("confirmOrder/{oid}")
    public Object confirmOrder(@PathVariable int oid) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.finish);
        orderService.update(o);
        return Result.success();
    }

    @PutMapping("deleteOrder/{oid}")
    public Object deleteOrder(@PathVariable int oid) {
        Order o = orderService.get(oid);
        if(o.getStatus().equals(OrderService.waitConfirm)) {
            return Result.success();
        }
        orderService.delete(o);
        return Result.success();
    }
}
