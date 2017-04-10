package com.assignment2.controller;

import com.assignment2.domain.Thread;
import com.assignment2.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
@Controller
public class HomeController {

    @Autowired
    ThreadService threadService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session){
        if(session.getAttribute("login") != null) {

        }
        List<Thread> threads = threadService.findAll();
        model.addAttribute("threads", threads);
        return "index";
    }
}
