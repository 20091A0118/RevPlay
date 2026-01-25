package com.revplay.model;

public class Album {
    private int albumId;
    private String title;

    public Album(int albumId, String title) {
        this.albumId = albumId;
        this.title = title;
    }

    @Override
    public String toString() {
        return albumId + " - " + title;
    }
}
