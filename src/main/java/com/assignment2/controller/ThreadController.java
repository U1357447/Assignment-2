package com.assignment2.controller;

import com.assignment2.domain.Thread;
import com.assignment2.service.ThreadService;
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

/**
 * Created by u1357447 on 07/04/17.
 */
@Controller
@RequestMapping(value = "/thread")
public class ThreadController {
    @Autowired
    ThreadService threadService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createView(Model model, HttpSession session){
        if(session.getAttribute("login") == null) {
            return "index";
        }

        Thread thread = new Thread();
        model.addAttribute("thread", thread);
        return "threads/createThread";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("thread") Thread thread, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            model.addAttribute("thread", thread);
            model.addAttribute("message", "Please provide information in each field");
            return "threads/createThread";
        }
        threadService.save(thread);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{thread}", method = RequestMethod.GET)
    public String editView(Model model, @PathVariable Thread thread){
        model.addAttribute("thread", thread);
        return "threads/createThread";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("thread") Thread thread){
        threadService.save(thread);
        return "redirect:/";
    }
}
