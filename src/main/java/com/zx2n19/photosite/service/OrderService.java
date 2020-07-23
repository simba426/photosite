package com.zx2n19.photosite.service;

import com.zx2n19.photosite.dao.OrderDAO;
import com.zx2n19.photosite.pojo.Order;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    UserService userService;

    public static final String waitPay = "waitPay";
    public static final String waitDelivery = "waitDelivery";
    public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";

    //作为卖家查找订单
    public Page4Navigator<Order> listByCid(User user, int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Order> pageFromJPA =orderDAO.findByUser(user, pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    //作为消费者查找订单
    public Page4Navigator<Order> listBySid(int sid, int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Order> pageFromJPA =orderDAO.findBySid(sid, pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public Order get(int id) {
        return orderDAO.findOne(id);
    }

    public void update(Order bean) {
        orderDAO.save(bean);
    }

    public void add(Order bean) {
        orderDAO.save(bean);
    }

    public void delete(Order bean) {
        orderDAO.delete(bean);
    }
}
