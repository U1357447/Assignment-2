package com.assignment2.controller;

import com.assignment2.domain.Reply;
import com.assignment2.domain.Thread;
import com.assignment2.service.ReplyService;
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
import java.util.List;

/**
 * Created by u1357447 on 07/04/17.
 */
@Controller
@RequestMapping(value = "/thread")
public class ThreadController {
    @Autowired
    ThreadService threadService;
    @Autowired
    ReplyService replyService;

    @RequestMapping(value = "/{thread}", method = RequestMethod.GET)
    public String viewThread(Model model, @PathVariable Thread thread){
        model.addAttribute("reply", new Reply());
        model.addAttribute("replies", thread.getReplies());
        model.addAttribute("thread", thread);
        return "threads/viewThread";
    }

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
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Please enter information in both fields");
            return "threads/createThread";
        }
        threadService.save(thread);
        model.addAttribute("type", "success");
        model.addAttribute("message", "Thread created successfully");

        List<Thread> threads = threadService.findAll();
        model.addAttribute("threads", threads);
        return "index";
    }

    @RequestMapping(value = "/edit/{thread}", method = RequestMethod.GET)
    public String editView(Model model, @PathVariable Thread thread){
        model.addAttribute("thread", thread);
        return "threads/editThread";
    }

    @RequestMapping(value = "/edit/{thread}", method = RequestMethod.POST)
    public String edit(Model model, @ModelAttribute("thread") Thread thread){
        threadService.save(thread);
        model.addAttribute("type", "success");
        model.addAttribute("message", "Thread updated successfully");

        List<Thread> threads = threadService.findAll();
        model.addAttribute("threads", threads);
        return "index";
    }

    @RequestMapping(value = "/delete/{thread}", method = RequestMethod.GET)
    public String deleteView(Model model, @PathVariable Thread thread){
        model.addAttribute("thread", thread);
        return "threads/deleteThread";
    }

    @RequestMapping(value = "/delete/{thread}", method = RequestMethod.POST)
    public String delete(Model model, @PathVariable Thread thread){
        int replies = thread.getReplies().size();
        for (int i=replies-1; i>=0; i--) {
            Reply reply = thread.getReplies().get(i);
            thread.getReplies().remove(reply);
            threadService.save(thread);
            replyService.delete(reply);
        }
        threadService.delete(thread);
        model.addAttribute("type", "success");
        model.addAttribute("message", "The thread and its replies have been deleted successfully");

        List<Thread> threads = threadService.findAll();
        model.addAttribute("threads", threads);
        return "index";
    }
}
