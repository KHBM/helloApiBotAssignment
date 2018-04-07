package com.assign.constant;

import org.springframework.web.bind.annotation.RequestMethod;

public enum RequestURLs {

    GET_BOTS(RequestMethod.GET, "/bots"),
    GET_BOT(RequestMethod.GET, "/bots/{bot}"),
    GET_ACTIONS(RequestMethod.GET, "/actions/{bot}"),
    GET_ACTION_CONTENT(RequestMethod.GET, "/actions/{bot}/{action}"),
    FETCH_NEXT_CONTENT(RequestMethod.PUT, "/content/{contentId}/choice/{choiceid}")
    ;
    
    private RequestMethod reqMethod;
    private String url;
    
    private RequestURLs(RequestMethod method, String url) {
        this.reqMethod = method;
        this.url = url;
    }

    public RequestMethod getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(RequestMethod reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
