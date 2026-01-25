package com.revplay.model;

public class SongStats {

    private int songId;
    private int playCount;
    private int favoriteCount;

    public SongStats() {}

    public SongStats(int songId, int playCount, int favoriteCount) {
        this.songId = songId;
        this.playCount = playCount;
        this.favoriteCount = favoriteCount;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    @Override
    public String toString() {
        return "SongStats{" +
                "songId=" + songId +
                ", playCount=" + playCount +
                ", favoriteCount=" + favoriteCount +
                '}';
    }
}