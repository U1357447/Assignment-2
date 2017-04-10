package com.assignment2.controller;

import com.assignment2.domain.User;
import com.assignment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session){
        if(session.getAttribute("login") != null){
            Thread thread = new Thread();
            model.addAttribute("thread", thread);
        }

        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "index";
    }
}
