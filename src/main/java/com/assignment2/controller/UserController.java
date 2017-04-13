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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ThreadService threadService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Please provide information in each field");
            return "user/register";
        }

        if(userService.checkUsername(user) == true){
            model.addAttribute("user", user);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Username already exists");
            return "user/register";
        }

        if((!(user.getIsAdmin().equals(""))) && (!(user.getIsAdmin().equals("ADMIN")))){
            model.addAttribute("user", user);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Entered token is invalid");
            return "user/register";
        }

        userService.save(user);
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView(Model model){
        LoginForm user = new LoginForm();
        model.addAttribute("user", user);
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @Valid @ModelAttribute("user") LoginForm user, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Username and password combination incorrect");
            return "user/login";
        }

        if(userService.validateLogin(user) == false) {
            model.addAttribute("user", user);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Username and password combination incorrect");
            return "user/login";
        }

        if(userService.loginCheck(user) == true) {
            model.addAttribute("user", user);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Your account has been banned by an administrator");
            return "user/login";
        }

        Long id = userService.getUserID(user);
        String name = userService.getUserName(user);
        Boolean admin = userService.isUserAdmin(user);
        session.setAttribute("login", id);
        session.setAttribute("loginName", name);
        session.setAttribute("admin", admin);
        Thread thread = new Thread();
        model.addAttribute("thread", thread);

        List<Thread> threads = threadService.findAll();
        model.addAttribute("threads", threads);
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutView(Model model, HttpSession session){
        session.removeAttribute("login");
        session.removeAttribute("loginName");
        session.removeAttribute("admin");
        return "redirect:/";
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public String AccountView(Model model, @PathVariable User user){
        List<Thread> threads = threadService.findAllByUser(user);
        model.addAttribute("threads", threads);
        model.addAttribute("user", user);
        return "user/viewAccount";
    }
}
