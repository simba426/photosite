package com.zx2n19.photosite.service;

import com.zx2n19.photosite.dao.LikedDAO;
import com.zx2n19.photosite.pojo.Liked;
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
public class LikedService {
    @Autowired
    LikedDAO likedDAO;
    @Autowired
    UserService userService;
    @Autowired
    PhotoService photoService;

    public void add(Liked bean) {
        likedDAO.save(bean);
    }

    public Liked get(int id) {
        return likedDAO.findOne(id);
    }

    public void delete(int id) {
        likedDAO.delete(id);
    }

    public void update(Liked bean) {
        likedDAO.save(bean);
    }

    public boolean isExist(User user, Photo photo) {
        Liked liked = likedDAO.getByUserAndPhoto(user, photo);
        return null!=liked;
    }

    public Page4Navigator<Liked> listUserComment(int uid, int start, int size, int navigatePages) {
        User user = userService.getById(uid);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);

        Page<Liked> pageFromJPA =likedDAO.findByUser(user, pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);

    }

}
