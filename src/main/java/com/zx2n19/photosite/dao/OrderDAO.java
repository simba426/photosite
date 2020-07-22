package com.zx2n19.photosite.dao;

import com.zx2n19.photosite.pojo.Order;
import com.zx2n19.photosite.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {

    Page<Order> findByUser(User user, Pageable pageable);
    Page<Order> findBySid(int sid, Pageable pageable);

    List<Order> findBySidOrderByIdDesc(int sid);
}
