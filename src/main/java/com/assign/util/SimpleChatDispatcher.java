package com.assign.util;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Response any chat string randomly.
 * For some special string, it will give a specific string to user.
 * 
 * In this simple implementation, it just gives chats by modular result.
 * @author kimilb
 *
 */
@Component
public class SimpleChatDispatcher implements ChatDispatcher {

    private SecureRandom random = new SecureRandom();
    
    @Override
    public String getSimpleChatString(String userInput, List<String> chatList) {
        
        String answer = null;
        
        
        if(userInput.contains("사랑")) {
            answer = "사랑해";
        } else if (userInput.contains("싫어")) {
            answer = "정말로?";
        }
        
        
        if(answer == null) {
            
            int seed = random.nextInt(chatList.size());
            if(!chatList.isEmpty()) {
                return chatList.get(seed);
            }        
            
            switch(seed % 10) {
                case 0:
                    answer = "♥";
                    break;
                case 1:
                    answer = "고민이 많구나..";
                    break;
                case 2:
                    answer = "본 메시지는 자동응답되었습니다.";
                    break;
                case 3:
                    answer = "심심해";
                    break;
                case 4:
                    answer = "이거 제작자 상당한 흔남이라던데...";
                    break;
                case 5:
                    answer = "너 뭐야, 방구 꼈지?";
                    break;
                case 6:
                    answer = "안 생겨요";
                    break;
                case 7:
                    answer = "그래도 안 생겨요";
                    break;
                case 8:
                    answer = "すみません。。。、韓国語で話せません。";
                    break;
                case 9:
                default:
                    answer = "미안해 못 알아듣겠어";
               
            }
        }
        return answer;
    }
}
