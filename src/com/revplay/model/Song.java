package com.revplay.model;


import java.util.Date;

public class Song {

    private int songId;
    private int artistId;
    private Integer albumId;
    private int genreId;
    private String title;
    private int durationSeconds;
    private Date releaseDate;
    private String fileUrl;
    private Integer playCount;
    private char isActive;
    private Date createdAt;

    public Song() {}

    public Song(int songId, int artistId, Integer albumId, int genreId, String title,
                int durationSeconds, Date releaseDate, String fileUrl,
                Integer playCount, char isActive, Date createdAt) {
        this.songId = songId;
        this.artistId = artistId;
        this.albumId = albumId;
        this.genreId = genreId;
        this.title = title;
        this.durationSeconds = durationSeconds;
        this.releaseDate = releaseDate;
        this.fileUrl = fileUrl;
        this.playCount = playCount;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getSongId() { return songId; }
    public void setSongId(int songId) { this.songId = songId; }

    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    public Integer getAlbumId() { return albumId; }
    public void setAlbumId(Integer albumId) { this.albumId = albumId; }

    public int getGenreId() { return genreId; }
    public void setGenreId(int genreId) { this.genreId = genreId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public Integer getPlayCount() { return playCount; }
    public void setPlayCount(Integer playCount) { this.playCount = playCount; }

    public char getIsActive() { return isActive; }
    public void setIsActive(char isActive) { this.isActive = isActive; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", albumId=" + albumId +
                ", genreId=" + genreId +
                ", durationSeconds=" + durationSeconds +
                '}';
    }
}

