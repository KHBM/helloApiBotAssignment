package com.assign.contoller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assign.repository.BotsRepository;

@RestController
@RequestMapping("/post")
public class ChatController {

    /*private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    O
    @Autowired
    private BotsRepository chatRepository;

    @RequestMapping("/write/{text}")
    public Chat write(@PathVariable("text")String text) {
        Chat chat = new Chat();
        chat.setContent(text);
        chat.setSubject("Deleted");
        chat.setRegDate(new Date());
        chatRepository.save(chat);
        logger.info("new log created {}", chat);
//        return "redirect:/post/" + chatRepository.save(chat).getChatIndex();
        return chat;
    }

    @RequestMapping("/list")
    public List<Chat> list(Model model) {
        List<Chat> postList = chatRepository.findAll();
        model.addAttribute("postList", postList);
        return postList;
    }

    @RequestMapping("/{id}")
    public Chat view(Model model, @PathVariable int id) {
        Chat chate = null;
        try {
            Chat chat = chatRepository.findByChatIndex(id);
            model.addAttribute("post", chat);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chate;
    }*/
}