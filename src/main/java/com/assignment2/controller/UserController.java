package com.assignment2.controller;
import com.assignment2.domain.User;
import com.assignment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by u1357447 on 07/04/17.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{user}", method = RequestMethod.GET)
    public String delete(@PathVariable User user){
        String name = user.getFirstname() + " " + user.getLastname();

        userService.delete(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{user}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable User user){
        model.addAttribute("user", user);
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/";
    }
}