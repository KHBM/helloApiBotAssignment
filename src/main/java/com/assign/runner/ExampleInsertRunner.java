package com.assign.runner;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.assign.domain.BotActionContent;
import com.assign.domain.BotActions;
import com.assign.domain.Bots;
import com.assign.domain.ChoiceType;
import com.assign.domain.Choices;
import com.assign.domain.ContentType;
import com.assign.repository.ActionContentRepository;
import com.assign.repository.BotActionsRepository;
import com.assign.repository.BotsRepository;
import com.assign.repository.ChoicesRepository;
import com.google.common.collect.Lists;

@Component
public class ExampleInsertRunner implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(ExampleInsertRunner.class);
    
    private final BotsRepository botRepository;
    private final BotActionsRepository actionRepository;
    private final ActionContentRepository contentRepository;
    private final ChoicesRepository choiceRepository;
    
    
    public ExampleInsertRunner(BotsRepository repository, BotActionsRepository actionRepository,
            ActionContentRepository contentRepository,
            ChoicesRepository choiceRepository) {
        this.botRepository = repository;
        this.actionRepository = actionRepository;
        this.contentRepository = contentRepository;
        this.choiceRepository = choiceRepository;
    }
    
    
    @Transactional
    @Override
    public void run(String... strings) throws Exception {
        List<Bots> allBots = insertTwoBots();
        
        List<BotActions> bot1Actions = insertActionOf(allBots.get(0));
        List<BotActions> bot2Actions = insertActionOf(allBots.get(1));
        
        bot1Actions.stream().filter(a -> {
            return a.isOpen();
        }).forEach(a -> {
            
        });
        
        BotActions a = bot1Actions.get(0);
        /*
        Choices yes = new Choices("응 그래", null, null);
        Choices no = new Choices("아니야", null, null);
        
        BotActionContent ac = new BotActionContent("hi", a.getActionId(),
                ChoiceType.BUTTON, ContentType.FIRST, 
                Lists.newArrayList(yes, no));
        
        BotActionContent newAc = contentRepository.save(ac);
        logger.info("action id is {}", newAc.getActionId());
        
        BotActionContent ac2 = new BotActionContent("okay bye", null,
                ChoiceType.NONE, ContentType.LAST, 
                null);
        
        BotActionContent newAc2 = contentRepository.save(ac2);
        logger.info("action id is {}", newAc2.getActionId());
        newAc.getChoices().stream().filter(c->c.getChoiceText().equals("아니야")).forEach(c -> c.setNextContentId(newAc2.getContentId()));
       
        //ac2.setChoices(Lists.newArrayList(no));
        //logger.info("Result {}", newAc2);
*/    
        BotActionContent ac = new BotActionContent("안녕, 애정운을 점쳐보자", a.getActionId(),
                ChoiceType.BUTTON, ContentType.FIRST,
                null);
        
        BotActionContent ac2 = new BotActionContent("알았어 다음 기회에!", null,
                ChoiceType.NONE, ContentType.LAST,
                null);
        
        BotActionContent ac3 = new BotActionContent("상대의 이름은?", null,
                ChoiceType.NAME, ContentType.MIDDLE,
                null);
        
        BotActionContent ac4 = new BotActionContent("카드 골라봐.", null,
                ChoiceType.CARD, ContentType.MIDDLE,
                null);
        
        BotActionContent ac41 = new BotActionContent("이 카드는?! 운이 좋아.", null,
                ChoiceType.BUTTON, ContentType.MIDDLE,
                null);
        
        BotActionContent ac42 = new BotActionContent("이 카드는?! 운이 나빠.", null,
                ChoiceType.BUTTON, ContentType.MIDDLE,
                null);
        
        BotActionContent ac5 = new BotActionContent("그렇구나, 더 궁금한 거 있어?", null,
                ChoiceType.TEXT, ContentType.MIDDLE,
                null);

        BotActionContent ac6 = new BotActionContent("복채 줘", null,
                ChoiceType.BUTTON, ContentType.MIDDLE,
                null);
        
        BotActionContent ac8 = new BotActionContent("내가 더 공부할게..", null,
                ChoiceType.NONE, ContentType.LAST,
                null);
        
        BotActionContent ac61 = new BotActionContent("고마워", null,
                ChoiceType.NONE, ContentType.LAST,
                null);
        
        BotActionContent ac62 = new BotActionContent("없구나. 다음에 또 봐.", null,
                ChoiceType.NONE, ContentType.LAST,
                null);
        
        BotActionContent ac51 = new BotActionContent("그 궁금증 다른 운세에서 볼 수 있을 거야!", null,
                ChoiceType.NONE, ContentType.LAST,
                null);
        
        BotActionContent ac52 = new BotActionContent("대화 마저 해", null,
                ChoiceType.TEXT, ContentType.MIDDLE,
                null);
        
        contentRepository.save(ac);
        contentRepository.save(ac2);
        contentRepository.save(ac3);
        contentRepository.save(ac4);
        contentRepository.save(ac41);
        contentRepository.save(ac42);
        contentRepository.save(ac5);
        contentRepository.save(ac51);
        contentRepository.save(ac52);
        contentRepository.save(ac6);
        contentRepository.save(ac61);
        contentRepository.save(ac62);
        contentRepository.save(ac8);
        
        Choices yes = new Choices("응 그래", ac3.getContentId(), ChoiceType.BUTTON);
        Choices no = new Choices("아니야", ac2.getContentId(), ChoiceType.BUTTON);
        ac.setChoices(Lists.newArrayList(yes, no));
        
        Choices name = new Choices("상대의 이름은?", ac4.getContentId(), ChoiceType.NAME);
        ac3.setChoices(Lists.newArrayList(name));
        
        Choices card1 = new Choices("카드1야", ac41.getContentId(), ChoiceType.CARD);
        Choices card2 = new Choices("카드2야", ac42.getContentId(), ChoiceType.CARD);
        ac4.setChoices(Lists.newArrayList(card1, card2));
        
        Choices good = new Choices("맞는 거 같아.", ac6.getContentId(), ChoiceType.BUTTON);
        Choices normal = new Choices("그저 그래.", ac5.getContentId(), ChoiceType.BUTTON);
        Choices bad = new Choices("맞지 않아", ac8.getContentId(), ChoiceType.BUTTON);
        ac41.setChoices(Lists.newArrayList(good, normal, bad));
        ac41.setChoices(Lists.newArrayList(good, normal, bad));
        
        Choices money = new Choices("여기 10만원", ac61.getContentId(), ChoiceType.BUTTON);
        Choices nomony = new Choices("나 돈 없어. 나중에.", ac62.getContentId(), ChoiceType.BUTTON);
        ac6.setChoices(Lists.newArrayList(money, nomony));
        
        Choices text = new Choices("", ac51.getContentId(), ChoiceType.TEXT);
        Choices notext = new Choices("", ac52.getContentId(), ChoiceType.NONE);
        ac5.setChoices(Lists.newArrayList(text, notext));
        
        ac52.setChoices(Lists.newArrayList(text, notext));
    }
        
    

    private List<BotActions> insertActionOf(Bots bot) {
        int botId = bot.getBotId();
        Stream.of(createBotActions("애정운"+botId, true, botId, true),
                    createBotActions("금전운"+botId, true, botId, true),
                    createBotActions("적란운"+botId, false, botId, true),
                    createBotActions("구수운"+botId, true, botId, false)
                ).forEach(action -> {
            actionRepository.save(action);
        });
        
        List<BotActions> allBotAction = actionRepository.findAll();
        allBotAction.forEach(System.out::println);
        
        return allBotAction;
    }
    
    private List<Bots> insertTwoBots() {
        Bots bot1 = createBots("라라", "This bot is the bot", "Sorry/There is no chance to see me again", ContentType.NONE);
        Bots bot2 = createBots("새새", "Angry dog bird", "Sorry guy/Go away", ContentType.NONE);
        
        // Top beers from https://www.beeradvocate.com/lists/top/
        Stream.of(bot1, bot2).forEach(bot -> {
                    botRepository.save(bot);
                });
        
        List<Bots> allBots = botRepository.findAll();
        allBots.forEach(System.out::println);
        
        return allBots;
    }
    
    private Bots createBots(String name, String desc, String byeMessage, ContentType type) {
        return new Bots(name, desc, byeMessage, type);
    }
    
    private BotActions createBotActions(String actionName, boolean isFree, int botId, boolean isOpen) {
        return new BotActions(actionName, isFree, botId, isOpen);
    }
}