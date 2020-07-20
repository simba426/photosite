package com.zx2n19.photosite.service;

import com.zx2n19.photosite.dao.ProductDAO;
import com.zx2n19.photosite.pojo.Product;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    UserService userService;

    public void add(Product bean) {
        productDAO.save(bean);
    }

    public Product get(int id) {
        return productDAO.findOne(id);
    }

    public void delete(int id) {
        productDAO.delete(id);
    }

    public void update(Product bean) {
        productDAO.save(bean);
    }

    public Page4Navigator<Product> listByUser(int uid, int start, int size, int navigatePages) {
        User user = userService.getById(uid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Product> pageFromJPA =productDAO.findByUser(user, pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public List<Product> listByUser(User user) {
        return productDAO.findByUserOrderByIdDesc(user);
    }

}
