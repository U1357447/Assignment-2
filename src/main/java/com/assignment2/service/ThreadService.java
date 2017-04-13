package com.assignment2.service;

import com.assignment2.domain.Thread;
import com.assignment2.domain.ThreadRepository;
import com.assignment2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by u1357447 on 10/04/17.
 */
@Service
public class ThreadService {
    @Autowired
    ThreadRepository threadRepository;

    public Thread save(Thread thread){
        return threadRepository.save(thread);
    }

    public void delete(Thread thread) {threadRepository.delete(thread); }

    public List<Thread> findAll() {
        return threadRepository.findAll();
    }

    public List<Thread> findAllByUser(User user) {
        Long id = user.getId();
        return threadRepository.findAllByUser(id);
    }
}
