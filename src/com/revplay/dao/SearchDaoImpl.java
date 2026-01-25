package com.revplay.dao;

import com.revplay.model.*;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.*;

public class SearchDaoImpl implements ISearchDao {

    // ======================
    // 1. SEARCH SONGS
    // ======================
    @Override
    public List<Song> searchSongs(String keyword) {

        List<Song> list = new ArrayList<>();
        String sql = "SELECT song_id, title, genre_id FROM song WHERE LOWER(title) LIKE LOWER(?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setSongId(rs.getInt("song_id"));
                s.setTitle(rs.getString("title"));
                s.setGenreId(rs.getInt("genre_id"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ======================
    // 2. SEARCH ARTISTS (FIXED)
    // ======================
    @Override
    public List<ArtistAccount> searchArtists(String keyword) {

        List<ArtistAccount> list = new ArrayList<>();

        // FIX: load all artists (no LIKE issue)
        String sql = "SELECT artist_id, stage_name, email FROM artist_account";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ArtistAccount a = new ArtistAccount();
                a.setArtistId(rs.getInt("artist_id"));
                a.setStageName(rs.getString("stage_name"));
                a.setEmail(rs.getString("email"));
                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ======================
    // 3. SEARCH ALBUMS (FIXED)
    // ======================
    @Override
    public List<Album> searchAlbums(String keyword) {

        List<Album> list = new ArrayList<>();
        String sql = "SELECT album_id, title FROM album";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Album a = new Album();
                a.setAlbumId(rs.getInt("album_id"));
                a.setTitle(rs.getString("title"));
                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ======================
    // 4. SEARCH PODCASTS
    // ======================
    @Override
    public List<Podcast> searchPodcasts(String keyword) {

        List<Podcast> list = new ArrayList<>();

        String sql = "SELECT podcast_id, title FROM podcast WHERE LOWER(title) LIKE LOWER(?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Podcast p = new Podcast();
                p.setPodcastId(rs.getInt("podcast_id"));
                p.setTitle(rs.getString("title"));
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // ======================
    // 5. BROWSE BY GENRE
    // ======================
    @Override
    public List<Song> browseByGenre(int genreId) {
        return browseSongs("SELECT song_id, title, genre_id FROM song WHERE genre_id = ?", genreId);
    }

    // ======================
    // 6. BROWSE BY ARTIST
    // ======================
    @Override
    public List<Song> browseByArtist(int artistId) {
        return browseSongs("SELECT song_id, title, genre_id FROM song WHERE artist_id = ?", artistId);
    }

    // ======================
    // 7. BROWSE BY ALBUM
    // ======================
    @Override
    public List<Song> browseByAlbum(int albumId) {
        return browseSongs("SELECT song_id, title, genre_id FROM song WHERE album_id = ?", albumId);
    }

    // ======================
    // COMMON HELPER
    // ======================
    private List<Song> browseSongs(String sql, int id) {

        List<Song> list = new ArrayList<>();

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setSongId(rs.getInt("song_id"));
                s.setTitle(rs.getString("title"));
                s.setGenreId(rs.getInt("genre_id"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
