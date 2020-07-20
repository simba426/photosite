package com.zx2n19.photosite.service;


import com.zx2n19.photosite.dao.PhotoDAO;
import com.zx2n19.photosite.pojo.Photo;
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
public class PhotoService {

    @Autowired
    PhotoDAO photoDAO;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    public void add(Photo bean) {
        photoDAO.save(bean);
    }

    public void delete(int id) {
        photoDAO.delete(id);
    }

    public Photo get(int id) {
        return photoDAO.findOne(id);
    }

    public void update(Photo bean) {
        photoDAO.save(bean);
    }

    public List<Photo> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return photoDAO.findAll(sort);
    }

    public List<Photo> listByUser(User user) {
        return photoDAO.findByUserOrderByIdDesc(user);
    }

    public void setCommentNumber(Photo photo) {
        photo.setCommentCount(commentService.getCount(photo));
    }

    public Page4Navigator<Photo> listByUser(int uid, int start, int size, int navigatePages) {
        User user = userService.getById(uid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Photo> pageFromJPA =photoDAO.findByUser(user, pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

}
