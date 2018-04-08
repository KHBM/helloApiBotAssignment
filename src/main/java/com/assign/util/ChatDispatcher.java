package com.assign.util;

import java.util.List;

/**
 * Interface for a response, any chat string randomly.
 * @author kimilb
 *
 */
public interface ChatDispatcher {
    String getSimpleChatString(String userInput, List<String> chatList);
}
