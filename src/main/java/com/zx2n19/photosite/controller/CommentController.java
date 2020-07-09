package com.zx2n19.photosite.controller;

import com.zx2n19.photosite.pojo.Comment;
import com.zx2n19.photosite.pojo.User;
import com.zx2n19.photosite.service.CommentService;
import com.zx2n19.photosite.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/photos/{pid}/comments")
    public Page4Navigator<Comment> listByPhoto(@PathVariable("pid") int pid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start<0?0:start;
        return commentService.listPhotoComment(pid, start, size,5);
    }

    @GetMapping("/comments")
    public Page4Navigator<Comment> listByUser(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, HttpSession session) {
        start = start<0?0:start;
        User user = (User)session.getAttribute("user");
        int uid = user.getId();
        return commentService.listUserComment(uid, start, size,5 );
    }

    @GetMapping("/comments/{id}")
    public Comment get(@PathVariable("id")int id) {
        return commentService.get(id);
    }

    @PostMapping("/comments")
    public void add(@RequestBody Comment bean, HttpSession session) {
        User user = (User)session.getAttribute("user");
        bean.setUser(user);
        bean.setCreateDate(new Date());
        commentService.add(bean);
    }

    @DeleteMapping("/comments/{id}")
    public String delete(@PathVariable("id")int id) {
        commentService.delete(id);
        return null;
    }

    @PutMapping("/comments")
    public void update(@RequestBody Comment bean) {
        commentService.update(bean);
    }
}
