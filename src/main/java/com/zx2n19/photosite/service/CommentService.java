package com.zx2n19.photosite.service;


import com.zx2n19.photosite.dao.CommentDAO;
import com.zx2n19.photosite.pojo.Comment;
import com.zx2n19.photosite.pojo.Photo;
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
public class CommentService {
    @Autowired
    CommentDAO commentDAO;
    @Autowired
    UserService userService;
    @Autowired
    PhotoService photoService;

    public void add(Comment bean) {
        commentDAO.save(bean);
    }

    public void delete(int id) {
        commentDAO.delete(id);
    }

    public void deleteByPhoto(int pid) {
        List<Comment> comments = commentDAO.findCommentByPhoto(photoService.get(pid));
        for(Comment i : comments) {
            commentDAO.delete(i.getId());
        }
    }

    public List<Comment> listByPhoto(Photo photo) {
        List<Comment> comments = commentDAO.findByPhotoOrderByIdDesc(photo);
        return comments;
    }

    public int getCount(Photo photo) {
        return commentDAO.countByPhoto(photo);
    }

    public void update(Comment bean) {
        commentDAO.save(bean);
    }

    public Comment get(int id) {
        return commentDAO.findOne(id);
    }

    public Page4Navigator<Comment> listByPhoto(int pid, int start, int size, int navigatePages) {
        Photo photo = photoService.get(pid);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);

        Page<Comment> pageFromJPA =commentDAO.findByPhoto(photo, pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }

    public Page4Navigator<Comment> listByUser(int uid, int start, int size, int navigatePages) {
        User user = userService.getById(uid);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);

        Page<Comment> pageFromJPA =commentDAO.findByUser(user, pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }
}
