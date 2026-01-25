package com.revplay.model;

public class Artist {
    private int artistId;
    private String stageName;

    public Artist(int artistId, String stageName) {
        this.artistId = artistId;
        this.stageName = stageName;
    }

    @Override
    public String toString() {
        return artistId + " - " + stageName;
    }
}
