package com.assign.service;

import java.util.List;

import com.assign.domain.BotActionContent;
import com.assign.domain.BotActions;
import com.assign.domain.Bots;

public interface ChatService {

    List<Bots> getAllBots();
    
    Bots getBot(int botId);
    
    List<BotActions> getBotActions(int botId);
    
    BotActions getBotAction(int botId, int actionId);
    
    BotActionContent getActionContent(int botId, int actionId);
    
    BotActionContent getNextActionContent(int contentId, int choiceId, String data);

    String getRandomChat(String typed);
}
