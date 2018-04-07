package com.assign.constant;

public class RequestURLs {

    public static final String GET_BOTS = "/bots";
    public static final String GET_BOT = "/bot/{bot}";
    public static final String GET_ACTIONS = "/actions/{bot}";
    public static final String GET_ACTION_CONTENT = "/content/{bot}/{action}";
    public static final String FETCH_NEXT_CONTENT = "/content/{contentId}/choice/{choiceId}";
    public static final String FETCH_NEXT_CONTENT_GET = "/content/{contentId}/choice/{choiceId}/{data}";
    public static final String GET_FREE_CHAT = "/chat/{data}";
}
