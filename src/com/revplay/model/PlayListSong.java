package com.revplay.model;
import java.time.LocalDateTime;

public class PlayListSong {

    private int playlistId;
    private int songId;
    private LocalDateTime addedAt;

    public PlayListSong(int playlistId, int songId) {
        this.playlistId = playlistId;
        this.songId = songId;
        this.addedAt = LocalDateTime.now();
    }

    // Getters
    public int getPlaylistId() { return playlistId; }
    public int getSongId() { return songId; }
    public LocalDateTime getAddedAt() { return addedAt; }
}

