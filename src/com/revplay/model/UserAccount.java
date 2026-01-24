package com.revplay.model;

public class UserAccount {
    private int userId;
    private String fullName;
    private PlayList playList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

    public UserAccount(int userId, String fullName, PlayList playList) {
        this.userId = userId;
        this.fullName = fullName;
        this.playList = playList;
    }
}
