package com.zx2n19.photosite.dao;

import com.zx2n19.photosite.pojo.Comment;
import com.zx2n19.photosite.pojo.Photo;
import com.zx2n19.photosite.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Integer> {
    Page<Comment> findByPhoto(Photo photo, Pageable pageable);
    Page<Comment> findByUser(User user, Pageable pageable);
}
