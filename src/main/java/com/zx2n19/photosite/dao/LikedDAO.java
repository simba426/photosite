package com.zx2n19.photosite.dao;

import com.zx2n19.photosite.pojo.Liked;
import com.zx2n19.photosite.pojo.Photo;
import com.zx2n19.photosite.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedDAO extends JpaRepository<Liked, Integer> {
    Page<Liked> findByUser(User user, Pageable pageable);
    Liked getByUserAndPhoto(User user, Photo photo);
}
