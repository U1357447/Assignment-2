package com.assignment2.controller;

import com.assignment2.domain.Thread;
import com.assignment2.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by u1357447 on 07/04/17.
 */
@Controller
@RequestMapping(value = "/thread")
public class ThreadController {
    @Autowired
    ThreadService threadService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String register(Model model, @Valid @ModelAttribute("thread") Thread thread, BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()) {
            model.addAttribute("thread", thread);
            model.addAttribute("message", "Please provide information in each field");
            return "index";
        }
        threadService.save(thread);
        return "redirect:/";
    }
}
