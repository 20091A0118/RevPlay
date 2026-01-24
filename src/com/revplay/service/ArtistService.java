package com.revplay.service;

import com.revplay.dao.*;
import com.revplay.model.*;

import java.util.List;

public class ArtistService {

    private ArtistDAO artistDAO = new ArtistDAO();
    private AlbumDAO albumDAO = new AlbumDAO();
    private SongDAO songDAO = new SongDAO();
    private SongStatsDAO songStatsDAO = new SongStatsDAO();

    public boolean registerArtist(Artist artist) {
        return artistDAO.registerArtist(artist);
    }

    public Artist loginArtist(String email, String password) {
        return artistDAO.loginArtist(email, password);
    }

    public Artist viewProfile(int artistId) {
        return artistDAO.viewProfile(artistId);
    }

    public boolean updateProfile(int artistId, String bio, String genre, String socials) {
        return artistDAO.updateProfile(artistId, bio, genre, socials);
    }

    public boolean createAlbum(Album album) {
        return albumDAO.createAlbum(album);
    }

    public List<Album> viewAlbumsByArtist(int artistId) {
        return albumDAO.viewAlbumsByArtist(artistId);
    }

    public boolean updateAlbum(int albumId, int artistId, String title, String genre, String releaseDate) {
        return albumDAO.updateAlbum(albumId, artistId, title, genre, releaseDate);
    }

    public boolean deleteAlbum(int albumId, int artistId) {
        return albumDAO.deleteAlbum(albumId, artistId);
    }

    public boolean uploadSong(Song song) {
        return songDAO.uploadSong(song);
    }

    public List<Song> viewSongsByArtist(int artistId) {
        return songDAO.viewSongsByArtist(artistId);
    }

    public boolean updateSong(int songId, int artistId, String title, String genre, int duration, String releaseDate) {
        return songDAO.updateSong(songId, artistId, title, genre, duration, releaseDate);
    }

    public boolean deleteSong(int songId, int artistId) {
        return songDAO.deleteSong(songId, artistId);
    }

    public void viewStats(int artistId) {
        songStatsDAO.viewStatsByArtist(artistId);
    }
}
