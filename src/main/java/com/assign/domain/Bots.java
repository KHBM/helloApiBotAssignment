package com.assign.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        uniqueConstraints={
            @UniqueConstraint(
                columnNames={"botName"}
            )
        }
    )
public class Bots {

    public Bots() {
    }
    
    public Bots(String botName, String description, String rejectMessage, ContentType lastFetchMode) {
        super();
        this.botName = botName;
        this.description = description;
        this.rejectMessage = rejectMessage;
        this.lastFetchMode = lastFetchMode;
    }

    @Id
    @GeneratedValue
    private int botId;
    
    /** show columns from bots; **/
    private String botName;
    
    private String description;
    
    private String rejectMessage;
    
    @Enumerated(EnumType.STRING)
    private ContentType lastFetchMode;

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    @Override
    public String toString() {
        return "Bots [botId=" + botId + ", botName=" + botName + ", description=" + description + ", rejectMessage="
                + rejectMessage + ", lastFetchMode=" + lastFetchMode + "]";
    }

    public void setLastFetchMode(ContentType lastFetchMode) {
        this.lastFetchMode = lastFetchMode;
    }
}
