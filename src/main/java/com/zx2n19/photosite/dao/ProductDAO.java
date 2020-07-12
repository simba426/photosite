package com.zx2n19.photosite.dao;

import com.zx2n19.photosite.pojo.Product;
import com.zx2n19.photosite.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    Page<Product> findByUser(User user, Pageable pageable);
}
