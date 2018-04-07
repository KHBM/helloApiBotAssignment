package com.assign.bean;

public class NickData {

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "NickData [nickName=" + nickName + "]";
    }
}
