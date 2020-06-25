package com.zx2n19.photosite.service;


import com.zx2n19.photosite.dao.UserDAO;
import com.zx2n19.photosite.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> list() {
        return userDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

}
