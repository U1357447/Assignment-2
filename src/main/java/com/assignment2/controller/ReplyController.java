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
@RequestMapping(value = "/reply")
public class ReplyController {
    @Autowired
    ThreadService threadService;
    @Autowired
    ReplyService replyService;

    @RequestMapping(value = "/create/{thread}", method = RequestMethod.GET)
    public String reply(Model model, @PathVariable Thread thread){
        model.addAttribute("reply", new Reply());
        model.addAttribute("replies", thread.getReplies());
        model.addAttribute("thread", thread);
        return "replies/createReply";
    }

    @RequestMapping(value = "/create/{thread}", method = RequestMethod.POST)
    public String createReply(Model model, @PathVariable Thread thread, @Valid @ModelAttribute("reply") Reply reply, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("reply", reply);
            model.addAttribute("replies", thread.getReplies());
            model.addAttribute("thread", thread);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Please enter a reply");
            return "replies/createReply";
        }

        replyService.save(reply);
        thread.getReplies().add(reply);
        threadService.save(thread);

        model.addAttribute("reply", new Reply());
        model.addAttribute("replies", thread.getReplies());
        model.addAttribute("thread", thread);

        model.addAttribute("type", "success");
        model.addAttribute("message", "Reply added successfully");

        return "threads/viewThread";
    }

    @RequestMapping(value = "/edit/{thread}/{reply}", method = RequestMethod.GET)
    public String editReplyView(Model model, @PathVariable Thread thread, @PathVariable Reply reply){
        model.addAttribute("thread", thread);
        model.addAttribute("reply", reply);
        return "replies/editReply";
    }

    @RequestMapping(value = "/edit/{thread}/{reply}", method = RequestMethod.POST)
    public String editReply(Model model, @PathVariable Thread thread, @Valid @ModelAttribute("reply") Reply reply, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("reply", reply);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Please enter a reply.");
            return "replies/editReply";
        }

        replyService.save(reply);

        model.addAttribute("thread", thread);
        model.addAttribute("reply", reply);
        model.addAttribute("replies", thread.getReplies());

        model.addAttribute("type", "success");
        model.addAttribute("message", "Reply has been updated successfully");

        return "threads/viewThread";
    }

    @RequestMapping(value = "/delete/{thread}/{reply}", method = RequestMethod.GET)
    public String deleteView(Model model, @PathVariable Reply reply, @PathVariable Thread thread){
        model.addAttribute("thread", thread);
        model.addAttribute("reply", reply);
        return "replies/deleteReply";
    }

    @RequestMapping(value = "/delete/{thread}/{reply}", method = RequestMethod.POST)
    public String delete(Model model, @PathVariable Thread thread, @PathVariable Reply reply){
        thread.getReplies().remove(reply);
        threadService.save(thread);
        replyService.delete(reply);

        model.addAttribute("reply", new Reply());
        model.addAttribute("replies", thread.getReplies());
        model.addAttribute("thread", thread);

        model.addAttribute("type", "success");
        model.addAttribute("message", "The reply has been deleted successfully");

        return "threads/viewThread";
    }
}
