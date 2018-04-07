package com.assign.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assign.domain.BotActionContent;
import com.assign.repository.ActionContentRepository;

@RestController
@RequestMapping("/content")
public class ActionContentController {

    private static final Logger logger = LoggerFactory.getLogger(ActionContentController.class);
    
    @Autowired
    private ActionContentRepository contentRepository;

    @RequestMapping("/list")
    public List<BotActionContent> list(Model model) {
        List<BotActionContent> postList = contentRepository.findAll();
        model.addAttribute("postList", postList);
        return postList;
    }
    
    @RequestMapping("/{id}")
    public BotActionContent view(Model model, @PathVariable int id) {
        BotActionContent data = null;
        try {
            data = contentRepository.findByContentId(id);
            model.addAttribute("post", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}