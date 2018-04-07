package com.assign.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assign.constant.RequestURLs;
import com.assign.domain.BotActionContent;
import com.assign.domain.BotActions;
import com.assign.domain.Bots;
import com.assign.domain.Choices;
import com.assign.service.ChatService;

@RestController
@RequestMapping("/")
public class ChatController {
    
    private final static Logger logger = LoggerFactory.getLogger(ChatController.class);
    
    @Autowired
    private ChatService chatService;

    @RequestMapping(value = RequestURLs.GET_BOTS, method=RequestMethod.GET)
    public List<Bots> getBots() {
        
        return chatService.getAllBots();
    }
    
    @GetMapping(RequestURLs.GET_BOT)
    public Bots getBot(@PathVariable("bot")int botId) {
        
        return chatService.getBot(botId);
    }
    
    @GetMapping(RequestURLs.GET_ACTIONS)
    public List<BotActions> getActions(@PathVariable("bot")int botId) {
        
        return chatService.getBotActions(botId);
    }
    
    @GetMapping(RequestURLs.GET_ACTION_CONTENT)
    public BotActionContent getActionContent(@PathVariable("bot")int botId, @PathVariable("action")int actionId) {
        
        return chatService.getActionContent(botId, actionId);
    }
    
    @PutMapping(RequestURLs.FETCH_NEXT_CONTENT)
    public BotActionContent fetchNextContent(@PathVariable("contentId")int contentId, @PathVariable("choiceId")int choiceId,
            @RequestBody String choiceData) {
        
        //FIXME: Should I have to consider the type of choice made?
        return chatService.getNextActionContent(contentId, choiceId, choiceData);
        
    }
}