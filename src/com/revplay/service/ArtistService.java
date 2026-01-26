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

    // ✅ Artist Registration
    public boolean registerArtist(ArtistAccount artist) {
        return artistDAO.registerArtist(artist);
    }

    // ✅ Artist Login
    public ArtistAccount loginArtist(String email, String passwordHash) {
        return artistDAO.loginArtist(email, passwordHash);
    }

    // ✅ View Profile
    public ArtistAccount viewProfile(int artistId) {
        return artistDAO.getArtistById(artistId);
    }

    // ✅ Update Profile
    public boolean updateProfile(int artistId, String bio, String genre,
                                 String instagramLink, String youtubeLink,
                                 String spotifyLink, String status) {
        return artistDAO.updateArtistProfile(artistId, bio, genre, instagramLink, youtubeLink, spotifyLink, status);
    }

    // ✅ Create Album (returns albumId)
    public int createAlbum(Album album) {
        return albumDAO.createAlbum(album);
    }

    // ✅ View Albums (only by artist)
    public List<Album> viewAlbums(int artistId) {
        return albumDAO.getAlbumsByArtist(artistId);
    }

    // ✅ Update Album
    public boolean updateAlbum(Album album) {
        return albumDAO.updateAlbum(album);
    }

    // ✅ Delete Album
    public boolean deleteAlbum(int albumId, int artistId) {
        return albumDAO.deleteAlbum(albumId, artistId);
    }

    // ✅ Upload Song
    public boolean uploadSong(Song song) {
        return songDAO.uploadSong(song);
    }

    // ✅ View Songs (only by artist)
    public List<Song> viewSongs(int artistId) {
        return songDAO.getSongsByArtist(artistId);
    }

    // ✅ View Songs by Album
    public List<Song> viewSongsByAlbum(int albumId) {
        return songDAO.getSongsByAlbum(albumId);
    }

    // ✅ Search Song
    public List<Song> searchSongs(String keyword) {
        return songDAO.searchSongsByTitle(keyword);
    }

    // ✅ Update Song
    public boolean updateSong(Song song) {
        return songDAO.updateSong(song);
    }

    // ✅ Delete Song
    public boolean deleteSong(int songId, int artistId) {
        return songDAO.deleteSong(songId, artistId);
    }

    // ✅ View Stats
    public void viewStats(int artistId) {
        songStatsDAO.viewStatsByArtist(artistId);
    }
}
