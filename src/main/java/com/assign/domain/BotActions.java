package com.assign.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BotActions {

    @Id
    @GeneratedValue
    private int actionId;
    
    private String actionName;
    
    private boolean isFree;
    
    private int botId;
    
    private boolean isOpen;

    public BotActions() {
    }
    
    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public String toString() {
        return "BotActions [actionId=" + actionId + ", actionName=" + actionName + ", isFree=" + isFree + ", botId="
                + botId + ", isOpen=" + isOpen + "]";
    }

    public BotActions(String actionName, boolean isFree, int botId, boolean isOpen) {
        super();
        this.actionName = actionName;
        this.isFree = isFree;
        this.botId = botId;
        this.isOpen = isOpen;
    }
    
    
}
