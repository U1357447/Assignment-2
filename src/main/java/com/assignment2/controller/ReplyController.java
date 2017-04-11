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

/**
 * Created by u1357447 on 07/04/17.
 */
@Controller
public class ReplyController {
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

    @RequestMapping(value = "/reply/{thread}", method = RequestMethod.GET)
    public String reply(Model model, @PathVariable Thread thread){
        model.addAttribute("reply", new Reply());
        model.addAttribute("replies", thread.getReplies());
        model.addAttribute("thread", thread);
        return "replies/createReply";
    }

    @RequestMapping(value = "/reply/{thread}", method = RequestMethod.POST)
    public String createReply(Model model, @PathVariable Thread thread, @Valid @ModelAttribute("reply") Reply reply, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("reply", reply);
            model.addAttribute("replies", thread.getReplies());
            model.addAttribute("thread", thread);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Please provide the body for the new note.");
            return "replies/createReplies";
        }

        replyService.save(reply);
        thread.getReplies().add(reply);
        threadService.save(thread);

        model.addAttribute("reply", new Reply());
        model.addAttribute("replies", thread.getReplies());
        model.addAttribute("thread", thread);

        model.addAttribute("type", "success");
        model.addAttribute("message", "Reply added successfully");

        return "threads/viewThreads";
    }

}
