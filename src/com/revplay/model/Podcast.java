package com.revplay.model;

public class Podcast {
    private int podcastId;
    private String title;

    public Podcast(int podcastId, String title) {
        this.podcastId = podcastId;
        this.title = title;
    }

    @Override
    public String toString() {
        return podcastId + " - " + title;
    }
}
