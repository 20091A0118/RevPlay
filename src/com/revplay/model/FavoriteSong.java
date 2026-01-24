package com.revplay.model;

import java.util.Date;

public class FavoriteSong {

    private int userId;
    private int songId;
    private Date favoritedAt;

    public FavoriteSong() {}

    public FavoriteSong(int userId, int songId, Date favoritedAt) {
        this.userId = userId;
        this.songId = songId;
        this.favoritedAt = favoritedAt;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getSongId() { return songId; }
    public void setSongId(int songId) { this.songId = songId; }

    public Date getFavoritedAt() { return favoritedAt; }
    public void setFavoritedAt(Date favoritedAt) { this.favoritedAt = favoritedAt; }

    @Override
    public String toString() {
        return "FavoriteSong{" +
                "userId=" + userId +
                ", songId=" + songId +
                ", favoritedAt=" + favoritedAt +
                '}';
    }
}
