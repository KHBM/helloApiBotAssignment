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
import com.assign.domain.BotChats;
import com.assign.domain.Bots;
import com.assign.domain.ChoiceType;
import com.assign.domain.Choices;
import com.assign.domain.ContentType;
import com.assign.repository.ActionContentRepository;
import com.assign.repository.BotActionsRepository;
import com.assign.repository.BotChatsRepository;
import com.assign.repository.BotsRepository;
import com.assign.repository.ChoicesRepository;
import com.google.common.collect.Lists;

/**
 * Runs in finishing starting server for the first time.
 * This will make chatting data and store it into h2 database.
 * @author kimilb
 *
 */
@Component
public class ExampleInsertRunner implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(ExampleInsertRunner.class);
    
    private final BotsRepository botRepository;
    private final BotActionsRepository actionRepository;
    private final ActionContentRepository contentRepository;
    private final ChoicesRepository choiceRepository;
    private final BotChatsRepository botChatRepository;
    
    
    public ExampleInsertRunner(BotsRepository repository, BotActionsRepository actionRepository,
            ActionContentRepository contentRepository,
            ChoicesRepository choiceRepository,
            BotChatsRepository botChatRepository) {
        this.botRepository = repository;
        this.actionRepository = actionRepository;
        this.contentRepository = contentRepository;
        this.choiceRepository = choiceRepository;
        this.botChatRepository = botChatRepository;
    }
    
    
    @Transactional
    @Override
    public void run(String... strings) throws Exception {
        List<Bots> allBots = insertTwoBots();
        
        List<BotActions> bot1Actions = insertActionOf(allBots.get(0));
        List<BotActions> bot2Actions = insertActionOf2(allBots.get(1));
        
        createBotChats1(allBots.get(0).getBotId());
        createBotChats2(allBots.get(1).getBotId());
        
        bot1Actions.stream().filter(a -> {
            return a.isOpen();
        }).forEach(a -> {
            
        });
        
        insertChatInfoForChoice1OfBot1(bot1Actions);
        insertChatInfoForChoice1OfBot2(bot2Actions);
    }


    private void insertChatInfoForChoice1OfBot1(List<BotActions> bot1Actions) {
        BotActions a = bot1Actions.get(0);
        
        BotActionContent ac = new BotActionContent("안녕, 애정운을 점쳐보자", a.getActionId(),
                ChoiceType.BUTTON, ContentType.FIRST,
                null);
        
        BotActionContent ac2 = new BotActionContent("알았어 다음 기회에!", null,
                ChoiceType.NONE, ContentType.LAST,
                null);
        
        BotActionContent ac3 = new BotActionContent("상대의 이름은?", null,
                ChoiceType.NAME, ContentType.MIDDLE,
                null);
        
        BotActionContent ac4 = new BotActionContent("가 상대 이름이구나..., 카드 골라봐.", null,
                ChoiceType.CARD, ContentType.MIDDLE,
                null);
        
        BotActionContent ac41 = new BotActionContent("이 카드는?! 운이 좋아.", null,
                ChoiceType.BUTTON, ContentType.MIDDLE,
                null);
        
        BotActionContent ac42 = new BotActionContent("이 카드는?! 운이 나빠.", null,
                ChoiceType.BUTTON, ContentType.MIDDLE,
                null);
        
        BotActionContent ac43 = new BotActionContent("이 카드는?! 조커 카드야!! 이걸로 네가 할 수 있는 건 무궁해!! 곧 훈녀/훈남을 만나게 될거야.", null,
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
        
        BotActionContent ac51 = new BotActionContent("가 더 알고싶구나....., 그 궁금증 다른 운세에서 볼 수 있을 거야!", null,
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
        contentRepository.save(ac43);
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
        Choices card3 = new Choices("카드3야", ac43.getContentId(), ChoiceType.CARD);
        ac4.setChoices(Lists.newArrayList(card1, card2, card3));
        
        Choices good = new Choices("맞는 거 같아.", ac6.getContentId(), ChoiceType.BUTTON);
        Choices normal = new Choices("그저 그래.", ac5.getContentId(), ChoiceType.BUTTON);
        Choices bad = new Choices("맞지 않아", ac8.getContentId(), ChoiceType.BUTTON);
        ac41.setChoices(Lists.newArrayList(good, normal, bad));
        
        Choices good2 = new Choices("맞는 거 같아.", ac6.getContentId(), ChoiceType.BUTTON);
        Choices normal2 = new Choices("그저 그래.", ac5.getContentId(), ChoiceType.BUTTON);
        Choices bad2 = new Choices("맞지 않아", ac8.getContentId(), ChoiceType.BUTTON);
        ac42.setChoices(Lists.newArrayList(good2, normal2, bad2));
        
        Choices good3 = new Choices("맞는 거 같아.", ac6.getContentId(), ChoiceType.BUTTON);
        Choices normal3 = new Choices("그저 그래.", ac5.getContentId(), ChoiceType.BUTTON);
        Choices bad3 = new Choices("맞지 않아", ac8.getContentId(), ChoiceType.BUTTON);
        ac43.setChoices(Lists.newArrayList(good3, normal3, bad3));
        
        Choices money = new Choices("여기 10만원", ac61.getContentId(), ChoiceType.BUTTON);
        Choices nomony = new Choices("나 돈 없어. 나중에.", ac62.getContentId(), ChoiceType.BUTTON);
        ac6.setChoices(Lists.newArrayList(money, nomony));
        
        Choices text = new Choices("", ac51.getContentId(), ChoiceType.TEXT);
        Choices notext = new Choices("", ac52.getContentId(), ChoiceType.NONE);
        ac5.setChoices(Lists.newArrayList(text, notext));
        
        Choices text2 = new Choices("", ac51.getContentId(), ChoiceType.TEXT);
        Choices notext2 = new Choices("", ac52.getContentId(), ChoiceType.NONE);
        ac52.setChoices(Lists.newArrayList(text2, notext2));
    }
        
    private void insertChatInfoForChoice1OfBot2(List<BotActions> bot2Actions) {
        BotActions a = bot2Actions.get(0);
        
        BotActionContent ac = new BotActionContent("누굴 욕해야 속이 씨원스레 뻥 뚤리겠으까? 할거야?", a.getActionId(),
                ChoiceType.BUTTON, ContentType.FIRST,
                null);
        
        BotActionContent ac2 = new BotActionContent("안할그면 와 했노!", null,
                ChoiceType.NONE, ContentType.LAST,
                null);
        
        BotActionContent ac3 = new BotActionContent("욕할 애 이름이 뭐고?", null,
                ChoiceType.NAME, ContentType.MIDDLE,
                null);
        
        BotActionContent ac4 = new BotActionContent("란 분이야?? 바보!!!", null,
                ChoiceType.BUTTON, ContentType.MIDDLE,
                null);
        
        BotActionContent ac41 = new BotActionContent("멍청이!! 어때, 속 시원히 풀렸으까?", null,
                ChoiceType.BUTTON, ContentType.MIDDLE,
                null);
        
        BotActionContent ac5 = new BotActionContent("자네도 욕해봐", null,
                ChoiceType.TEXT, ContentType.MIDDLE,
                null);

        BotActionContent ac51 = new BotActionContent(", 얼씨구야 잘한데이~, 좋았으 그렇게 욕해야제. 잘했어. 수고해", null,
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
        contentRepository.save(ac5);
        contentRepository.save(ac51);
        contentRepository.save(ac52);
        
        Choices yes = new Choices("욕해야지", ac3.getContentId(), ChoiceType.BUTTON);
        Choices no = new Choices("아니 안해야지", ac2.getContentId(), ChoiceType.BUTTON);
        ac.setChoices(Lists.newArrayList(yes, no));
        
        Choices name = new Choices("그놈의 이름은?", ac4.getContentId(), ChoiceType.NAME);
        ac3.setChoices(Lists.newArrayList(name));
        
        Choices btn = new Choices("계속해. 옳소!", ac41.getContentId(), ChoiceType.BUTTON);
        ac4.setChoices(Lists.newArrayList(btn));
        
        Choices normal = new Choices("거럼", ac5.getContentId(), ChoiceType.BUTTON);
        ac41.setChoices(Lists.newArrayList(normal));
        
        Choices text = new Choices("", ac51.getContentId(), ChoiceType.TEXT);
        Choices notext = new Choices("", ac52.getContentId(), ChoiceType.NONE);
        ac5.setChoices(Lists.newArrayList(text, notext));
        
        Choices text2 = new Choices("", ac51.getContentId(), ChoiceType.TEXT);
        Choices notext2 = new Choices("", ac52.getContentId(), ChoiceType.NONE);
        ac52.setChoices(Lists.newArrayList(text2, notext2));
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
        
        List<BotActions> allBotAction = actionRepository.findByBotId(botId);
        allBotAction.forEach(System.out::println);
        
        return allBotAction;
    }
    
    private List<BotActions> insertActionOf2(Bots bot) {
        int botId = bot.getBotId();
        Stream.of(createBotActions("욕설"+botId, true, botId, true),
                    createBotActions("칭찬"+botId, true, botId, false)
                ).forEach(action -> {
            actionRepository.save(action);
        });
        
        List<BotActions> allBotAction = actionRepository.findByBotId(botId);
        allBotAction.forEach(System.out::println);
        
        return allBotAction;
    }
    
    private List<Bots> insertTwoBots() {
        Bots bot1 = createBots("라라", "안녕하세요, 2302살 먹은 라라님 되십니다. 운을 점쳐드려요. 메뉴를 이용해 봐요.", "Sorry/There is no chance to see me again", ContentType.NONE);
        Bots bot2 = createBots("새새", "안녕하신가! 난 독설가요, 어디 어떤 말을 해주까? 메뉴 이용해 봐.", "Sorry guy/Go away", ContentType.NONE);
        
        // Top beers from https://www.beeradvocate.com/lists/top/
        Stream.of(bot1, bot2).forEach(bot -> {
                    botRepository.save(bot);
                });
        
        List<Bots> allBots = botRepository.findAll();
        allBots.forEach(System.out::println);
        
        return allBots;
    }
    
    private void createBotChats1(final int botId) {
        Stream.of("♥", "고민이 많구나..", "본 메시지는 자동응답되었습니다.", "심심해", "이거 제작자 상당한 흔남이라던데...",
                "너 뭐야, 방구 꼈지?", "안 생겨요", "그래도 안 생겨요", "すみません。。。、韓国語で話せません。",
                "미안해 못 알아듣겠어").forEach(txt -> {
                    BotChats chat = new BotChats(txt, botId); 
                    botChatRepository.save(chat);
        });
    }
    
    private void createBotChats2(final int botId) {
        Stream.of("잠좀 자자", "왜 불러", "한가하냐?", "저리 가서 놀아", "내 이름은 새새",
                "나 트름한다? 절루가", "모르겠어", "할말 있으면 메뉴를 클릭해", "うんこ",
                "동해물과 백두산이 마르고 닳도록 맞자").forEach(txt -> {
                    BotChats chat = new BotChats(txt, botId); 
                    botChatRepository.save(chat);
        });
    }
    
    private Bots createBots(String name, String desc, String byeMessage, ContentType type) {
        return new Bots(name, desc, byeMessage, type);
    }
    
    private BotActions createBotActions(String actionName, boolean isFree, int botId, boolean isOpen) {
        return new BotActions(actionName, isFree, botId, isOpen);
    }
}