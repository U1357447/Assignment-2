package com.assignment2.service;

import com.assignment2.domain.Reply;
import com.assignment2.domain.ReplyRepository;
import com.assignment2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by u1357447 on 10/04/17.
 */
@Service
public class ReplyService {
    @Autowired
    ReplyRepository replyRepository;

    public Reply save(Reply reply){
        return replyRepository.save(reply);
    }

    public void delete(Reply reply) {replyRepository.delete(reply); }

    public List<Reply> findAll() {
        return replyRepository.findAll();
    }

    public List<Reply> findAllByUser(User user) {
        String id = user.getId().toString();
        return replyRepository.findAllByUser(id);
    }
}
