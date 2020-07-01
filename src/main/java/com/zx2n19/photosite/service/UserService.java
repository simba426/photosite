package com.zx2n19.photosite.service;


import com.zx2n19.photosite.dao.UserDAO;
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
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Page4Navigator<User> list(int start, int size, int navigatePages) { //size参数表示每页显示5个category
        Page pageFromJPA = userDAO.findAll(PageRequest.of(start, size, Sort.by(Sort.Direction.DESC, "id")));
        return new Page4Navigator<>(pageFromJPA, navigatePages);
    }

    public List<User> list() {
        return userDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

}
