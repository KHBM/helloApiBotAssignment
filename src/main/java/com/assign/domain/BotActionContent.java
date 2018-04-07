package com.assign.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class BotActionContent {

    @Id
    @GeneratedValue
    //@ManyToOne
    private int contentId;
    
    private String contentString;
    
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "content")
    //@JoinColumn(name = "CONTENT_ID")
    //FIX: I won't let this have relationship with botAction table forcibly cuz it makes things complex.
    private Integer actionId;
    
    @Enumerated(EnumType.STRING)
    private ChoiceType nextChoiceType;
    
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="content_Id")
    private List<Choices> choices;
    
    public BotActionContent() {
    }

    @Override
    public String toString() {
        return "BotActionContent [contentId=" + contentId + ", contentString=" + contentString + ", actionId="
                + actionId + ", nextChoiceType=" + nextChoiceType + ", contentType=" + contentType + ", choices="
                + choices + "]";
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getContentString() {
        return contentString;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public ChoiceType getNextChoiceType() {
        return nextChoiceType;
    }

    public void setNextChoiceType(ChoiceType nextChoiceType) {
        this.nextChoiceType = nextChoiceType;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public BotActionContent(String contentString, Integer actionId, ChoiceType nextChoiceType, ContentType contentType,
            List<Choices> choices) {
        super();
        this.contentString = contentString;
        this.actionId = actionId;
        this.nextChoiceType = nextChoiceType;
        this.contentType = contentType;
        this.choices = choices;
    }
}
