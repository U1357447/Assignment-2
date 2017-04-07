package com.assignment2.service;

import com.assignment2.domain.User;
import com.assignment2.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User u){ return userRepository.save(u); }

    public List<User> findAll() { return userRepository.findAll(); }

    public void delete(User user) {userRepository.delete(user); }
}
