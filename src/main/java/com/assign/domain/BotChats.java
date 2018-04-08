package com.assign.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BotChats {

    @Id
    @GeneratedValue
    private int chatIndex;
    
    private String chatString;
    
    private int botId;

    @Override
    public String toString() {
        return "BotChats [chatIndex=" + chatIndex + ", chatString=" + chatString + ", botId=" + botId + "]";
    }

    public int getChatIndex() {
        return chatIndex;
    }

    public void setChatIndex(int chatIndex) {
        this.chatIndex = chatIndex;
    }

    public String getChatString() {
        return chatString;
    }

    public void setChatString(String chatString) {
        this.chatString = chatString;
    }

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public BotChats() {
    }
    
    public BotChats(String chatString, int botId) {
        super();
        this.chatString = chatString;
        this.botId = botId;
    }
}
