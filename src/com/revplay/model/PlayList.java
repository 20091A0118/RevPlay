package com.revplay.model;





import java.time.LocalDateTime;

public class PlayList {
    private int playlistId;
    private int userId;
    private String name;
    private String description;
    private String privacyStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor for creating a new playlist
    public PlayList(int userId, String name, String description, String privacyStatus) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.privacyStatus = privacyStatus;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor for fetching playlist from DB
    public PlayList(int playlistId, int userId, String name, String description, String privacyStatus,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.playlistId = playlistId;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.privacyStatus = privacyStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters & Setters
    public int getPlaylistId() { return playlistId; }
    public void setPlaylistId(int playlistId) { this.playlistId = playlistId; }
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; this.updatedAt = LocalDateTime.now(); }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; this.updatedAt = LocalDateTime.now(); }
    public String getPrivacyStatus() { return privacyStatus; }
    public void setPrivacyStatus(String privacyStatus) { this.privacyStatus = privacyStatus; this.updatedAt = LocalDateTime.now(); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}










