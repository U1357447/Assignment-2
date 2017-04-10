package com.assignment2.service;

import com.assignment2.domain.Thread;
import com.assignment2.domain.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by u1357447 on 10/04/17.
 */
@Service
public class ThreadService {
    @Autowired
    ThreadRepository threadRepository;

    public Thread save(Thread u){
        return threadRepository.save(u);
    }
}
