package com.revplay.model;

public class Song {

    private int songId;
    private int artistId;
    private Integer albumId; // nullable
    private String title;
    private String genre;
    private int durationSec;
    private String releaseDate; // YYYY-MM-DD

    public Song() {}

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(int durationSec) {
        this.durationSec = durationSec;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", artistId=" + artistId +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", durationSec=" + durationSec +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
