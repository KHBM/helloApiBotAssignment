package com.assign.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assign.domain.BotActionContent;
import com.assign.domain.BotActions;
import com.assign.domain.Bots;
import com.assign.domain.Choices;
import com.assign.repository.ActionContentRepository;
import com.assign.repository.BotActionsRepository;
import com.assign.repository.BotsRepository;
import com.assign.repository.ChoicesRepository;

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
            
            logger.info("{} is choosenChoice", choosenChoice);
            logger.info("{} is findBychoice", findByChoiceId);
            
            return contentRepository.findByContentId(choosenChoice.getNextContentId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
