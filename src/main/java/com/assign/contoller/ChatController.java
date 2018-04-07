package com.assign.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assign.bean.NickData;
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
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Bots> getBots() {
        
        return chatService.getAllBots();
    }
    
    @GetMapping(RequestURLs.GET_BOT)
    @CrossOrigin(origins = "http://localhost:3000")
    public Bots getBot(@PathVariable("bot")int botId) {
        
        return chatService.getBot(botId);
    }
    
    @GetMapping(RequestURLs.GET_ACTIONS)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<BotActions> getActions(@PathVariable("bot")int botId) {
        
        return chatService.getBotActions(botId);
    }
    
    @GetMapping(RequestURLs.GET_ACTION_CONTENT)
    @CrossOrigin(origins = "http://localhost:3000")
    public BotActionContent getActionContent(@PathVariable("bot")int botId, @PathVariable("action")int actionId) {
        
        return chatService.getActionContent(botId, actionId);
    }
    
    //FIXME: csrf info prevents this controller from being called by other browser.
    @PostMapping(RequestURLs.FETCH_NEXT_CONTENT)
    public BotActionContent fetchNextContent(@PathVariable("contentId")int contentId, @PathVariable("choiceId")int choiceId,
            @RequestBody NickData choiceData) {
        
        //FIXME: Should I have to consider the type of choice made?
        return chatService.getNextActionContent(contentId, choiceId, choiceData.getNickName());
        
    }
    
    @GetMapping(RequestURLs.FETCH_NEXT_CONTENT_GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public BotActionContent fetchNextContents(@PathVariable("contentId")int contentId, @PathVariable("choiceId")int choiceId,
            @PathVariable("data") String choiceData) {
        
        return chatService.getNextActionContent(contentId, choiceId, choiceData);
        
    }
    
    @GetMapping(RequestURLs.GET_FREE_CHAT)
    @CrossOrigin(origins = "http://localhost:3000")
    public String getRandomChat(@PathVariable("data") String typed) {
        try {
            return chatService.getRandomChat(typed);
        } catch (Exception e) {
            e.printStackTrace();
            return "공백은 적지마";
        }
    }
}