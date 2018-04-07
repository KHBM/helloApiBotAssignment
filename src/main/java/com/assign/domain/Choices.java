package com.assign.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Choices {

    @Id
    @GeneratedValue
    private int choiceId;
    
    private String choiceText;
    
    private Integer nextContentId;
    
    @Column(name="content_id")
    private Integer contentId;
    
    private ChoiceType choiceType;

    public Choices() {
    }
    
    public Choices(String choiceText, Integer nextContentId, ChoiceType choiceType) {
        super();
        this.choiceText = choiceText;
        this.nextContentId = nextContentId;
        this.choiceType = choiceType;
    }

    @Override
    public String toString() {
        return "Choices [choiceId=" + choiceId + ", choiceText=" + choiceText + ", nextContentId=" + nextContentId
                + ", contentId=" + contentId + ", choiceType=" + choiceType + "]";
    }

    public int getChoiceId() {
        return choiceId;
    }

    public Choices setChoiceId(int choiceId) {
        this.choiceId = choiceId;
        return this;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public Integer getNextContentId() {
        return nextContentId;
    }

    public void setNextContentId(Integer nextContentId) {
        this.nextContentId = nextContentId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Choices setContentId(Integer contentId) {
        this.contentId = contentId;
        return this;
    }

    public ChoiceType getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(ChoiceType choiceType) {
        this.choiceType = choiceType;
    }
}
