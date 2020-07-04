package com.zx2n19.photosite.dao;

import com.zx2n19.photosite.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String name);

    User getByUsernameAndPassword(String username, String password);
}
