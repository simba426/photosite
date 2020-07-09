package com.zx2n19.photosite.service;


import com.zx2n19.photosite.dao.CommentDAO;
import com.zx2n19.photosite.pojo.Comment;
import com.zx2n19.photosite.pojo.Photo;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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



    /***********************做到这里***********************/
    public void deleteByPhoto(Photo photo) {
        commentDAO.deleteByPhoto(photo);
    }

    public void update(Comment bean) {
        commentDAO.save(bean);
    }

    public Comment get(int id) {
        return commentDAO.findOne(id);
    }

    public Page4Navigator<Comment> listPhotoComment(int pid, int start, int size, int navigatePages) {
        Photo photo = photoService.get(pid);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);

        Page<Comment> pageFromJPA =commentDAO.findByPhoto(photo, pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }

    public Page4Navigator<Comment> listUserComment(int uid, int start, int size, int navigatePages) {
        User user = userService.getById(uid);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);

        Page<Comment> pageFromJPA =commentDAO.findByUser(user, pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }
}
