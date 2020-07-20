package com.zx2n19.photosite.dao;

import com.zx2n19.photosite.pojo.Photo;
import com.zx2n19.photosite.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoDAO extends JpaRepository<Photo,Integer> {
    Page<Photo> findByUser(User user, Pageable pageable);
    List<Photo> findByUserOrderByIdDesc(User user);
}
