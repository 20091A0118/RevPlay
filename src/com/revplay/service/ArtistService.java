package com.revplay.service;

import com.revplay.dao.*;
import com.revplay.model.Album;
import com.revplay.model.ArtistAccount;
import com.revplay.model.Song;

import java.util.List;

public class ArtistService {

    private IArtistDAO artistDAO = new ArtistDAOImpl();
    private IAlbumDAO albumDAO = new AlbumDAOImpl();
    private ISongDAO songDAO = new SongDAOImpl();
    private ISongStatsDAO songStatsDAO = new SongStatsDAOImpl();

    public boolean registerArtist(ArtistAccount artist) {
        return artistDAO.registerArtist(artist);
    }

    public ArtistAccount loginArtist(String email, String passwordHash) {
        return artistDAO.loginArtist(email, passwordHash);
    }

    public ArtistAccount viewProfile(int artistId) {
        return artistDAO.getArtistById(artistId);
    }

    public boolean updateProfile(int artistId, String bio, String genre,
                                 String instagramLink, String youtubeLink,
                                 String spotifyLink, String status) {
        return artistDAO.updateArtistProfile(artistId, bio, genre, instagramLink, youtubeLink, spotifyLink, status);
    }

    public boolean createAlbum(Album album) {
        return albumDAO.createAlbum(album);
    }

    public List<Album> viewAlbums(int artistId) {
        return albumDAO.getAlbumsByArtist(artistId);
    }

    public boolean updateAlbum(Album album) {
        return albumDAO.updateAlbum(album);
    }

    public boolean deleteAlbum(int albumId, int artistId) {
        return albumDAO.deleteAlbum(albumId, artistId);
    }

    public boolean uploadSong(Song song) {
        return songDAO.uploadSong(song);
    }

    public List<Song> viewSongs(int artistId) {
        return songDAO.getSongsByArtist(artistId);
    }

    public boolean updateSong(Song song) {
        return songDAO.updateSong(song);
    }

    public boolean deleteSong(int songId, int artistId) {
        return songDAO.deleteSong(songId, artistId);
    }

    public void viewStats(int artistId) {
        songStatsDAO.viewStatsByArtist(artistId);
    }
}
