package com.assignment2.controller;

import com.assignment2.domain.LoginForm;
import com.assignment2.domain.Thread;
import com.assignment2.domain.User;
import com.assignment2.domain.UserSearchForm;
import com.assignment2.service.ThreadService;
import com.assignment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/update/{user}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable User user){
        model.addAttribute("user", user);
        return "user/update";
    }

    @RequestMapping(value = "/admin/update/{user}", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("user") User user){
        userService.save(user);
        model.addAttribute("type", "success");
        model.addAttribute("message", "The user has been updated successfully");

        UserSearchForm searchForm = new UserSearchForm();
        List<User> users = userService.findAll();
        model.addAttribute("searchCriteria", searchForm);
        model.addAttribute("searchedUsers", users);
        model.addAttribute("users", userService.findAll());
        return "user/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model){
        UserSearchForm searchForm = new UserSearchForm();
        List<User> users = userService.findAll();
        model.addAttribute("searchCriteria", searchForm);
        model.addAttribute("searchedUsers", users);
        model.addAttribute("users", userService.findAll());
        return "user/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute("searchCriteria") UserSearchForm searchForm){
        List<User> users = userService.searchUsers(searchForm);
        model.addAttribute("searchCriteria", searchForm);
        model.addAttribute("searchedUsers", users);
        model.addAttribute("users", userService.findAll());
        return "user/admin";
    }

    @RequestMapping(value = "/admin/ban/{user}", method = RequestMethod.POST)
    public String ban(Model model, @PathVariable User user){
        UserSearchForm searchForm = new UserSearchForm();
        List<User> users = userService.findAll();
        model.addAttribute("searchCriteria", searchForm);
        model.addAttribute("searchedUsers", users);
        model.addAttribute("users", userService.findAll());

        if(userService.isUserBanned(user) == true) {
            user.setBan(false);
            userService.save(user);
            model.addAttribute("type", "success");
            model.addAttribute("message", "User has been unbanned");
            return "user/admin";
        }
        if(userService.isUserBanned(user) == false){
            user.setBan(true);
            userService.save(user);
            model.addAttribute("type", "success");
            model.addAttribute("message", "User has been banned");
            return "user/admin";
        }
        return "user/admin";
    }
}
