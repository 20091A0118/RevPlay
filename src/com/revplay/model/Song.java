package com.revplay.model;

public class Song {
    private int songId;
    private String title;
    private int genreId;

    public Song(int songId, String title, int genreId) {
        this.songId = songId;
        this.title = title;
        this.genreId = genreId;
    }

    public int getSongId() { return songId; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return songId + " - " + title;
    }
}
