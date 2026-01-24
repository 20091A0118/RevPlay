package com.revplay.model;

public class Artist {
    private int artistId;
    private String name;
    private String email;
    private String password;
    private String bio;
    private String genre;
    private String socials;

    public Artist() {}

    public Artist(int artistId, String name, String email, String password, String bio, String genre, String socials) {
        this.artistId = artistId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.genre = genre;
        this.socials = socials;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSocials() {
        return socials;
    }

    public void setSocials(String socials) {
        this.socials = socials;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", genre='" + genre + '\'' +
                ", socials='" + socials + '\'' +
                '}';
    }
}

