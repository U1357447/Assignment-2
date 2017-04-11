package com.assignment2.service;

import com.assignment2.domain.Reply;
import com.assignment2.domain.ReplyRepository;
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

    public List<Reply> findAll() {
        return replyRepository.findAll();
    }
}
