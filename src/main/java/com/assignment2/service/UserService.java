package com.assignment2.service;

import com.assignment2.domain.LoginForm;
import com.assignment2.domain.UserSearchForm;
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

    public boolean checkUsername(User user){
        List<User> users = userRepository.findByUsername(user.getUsername());

        return users != null && users.size() > 0;
    }

    public boolean validateLogin(LoginForm user) {
        List<User> users = userRepository.checkUserInput(user.getUsername(), user.getPassword());
        return users != null && users.size() > 0;
    }

    public List<User> searchUsers(UserSearchForm user){
        return userRepository.searchUsers(user.getUsername());
    }

    public Long getUserID(LoginForm user){
        User ret = userRepository.getUser(user.getUsername());
        return ret.getId();
    }

    public String getUserName(LoginForm user){
        User ret = userRepository.getUser(user.getUsername());
        return ret.getFirstname() + " " + ret.getLastname();
    }
}
