package com.assign.service;

import java.security.SecureRandom;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assign.domain.BotActionContent;
import com.assign.domain.BotActions;
import com.assign.domain.BotChats;
import com.assign.domain.Bots;
import com.assign.domain.ChoiceType;
import com.assign.domain.Choices;
import com.assign.repository.ActionContentRepository;
import com.assign.repository.BotActionsRepository;
import com.assign.repository.BotChatsRepository;
import com.assign.repository.BotsRepository;
import com.assign.repository.ChoicesRepository;
import com.assign.util.ChatDispatcher;
import com.google.common.collect.Lists;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);
    
    @Autowired
    private ActionContentRepository contentRepository;
    @Autowired
    private BotsRepository botRepository;
    @Autowired
    private BotActionsRepository actionRepository;
    @Autowired
    private ChoicesRepository choiceRepository;
    @Autowired
    private BotChatsRepository botChatsRepository;
    @Autowired
    private ChatDispatcher simpleChatDispatcher;
    
    private SecureRandom random = new SecureRandom();
    
    @PostConstruct
    public void postConstruct() {
        
    }

    @Override
    public List<Bots> getAllBots() {
        return botRepository.findAll();
    }

    @Override
    public Bots getBot(int botId) {
        return botRepository.findByBotId(botId);
    }

    @Override
    public List<BotActions> getBotActions(int botId) {
        
        return actionRepository.findByBotId(botId);
    }

    @Override
    public BotActions getBotAction(int botId, int actionId) {
        
        return actionRepository.findByActionId(actionId);
    }

    @Override
    public BotActionContent getActionContent(int botId, int actionId) {
        
        return contentRepository.findByActionId(actionId);
    }

    @Override
    public BotActionContent getNextActionContent(int contentId, int choiceId, String data) {
        try {
            BotActionContent contents = contentRepository.findByContentId(contentId);
            List<Choices> choices = choiceRepository.findByContentId(contentId);
            Choices findByChoiceId = choiceRepository.findByChoiceId(choiceId);
            
            logger.info("For content id {} Chosen choiceId {}, data {} and fetched {}", contentId, choiceId, data, choices);
            
            Choices choosenChoice = null;
            choosenChoice = choices.stream().filter(c -> c.getChoiceId() == choiceId).findFirst().get();
            
            if (choosenChoice.getChoiceType().equals(ChoiceType.CARD)) {
                int size = choices.size();
                int randNum = random.nextInt(size);
                choosenChoice = choices.get(randNum);
            }
           
            logger.info("{} is choosenChoice", choosenChoice);
            logger.info("{} is findBychoice", findByChoiceId);
            
            BotActionContent nextContents = contentRepository.findByContentId(choosenChoice.getNextContentId());
            
            if (choosenChoice.getChoiceType().equals(ChoiceType.NAME)) {
                nextContents.setContentString(data+ nextContents.getContentString());
            }
            
            else if (choosenChoice.getChoiceType().equals(ChoiceType.TEXT)) {
                nextContents.setContentString(data+ nextContents.getContentString());
            } 
            
            return nextContents;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getRandomChat(int botId, String typed) {
        List<BotChats> botChats = botChatsRepository.findByBotId(botId);
        if(botChats != null) {
            List<String> botChatStringList = Lists.transform(botChats, bc -> bc.getChatString());
            return simpleChatDispatcher.getSimpleChatString(typed, botChatStringList);
        } else {
            return simpleChatDispatcher.getSimpleChatString(typed, Lists.newArrayList());
        }
    }
}
