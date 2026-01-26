package com.revplay.dao;

import com.revplay.model.*;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.*;

public class SearchDaoImpl implements ISearchDao {

    @Override
    public List<Song> searchSongs(String keyword) {
        List<Song> list = new ArrayList<>();

        String sql;
        if (keyword == null || keyword.trim().isEmpty()) {
            sql = "SELECT song_id, title, genre_id FROM song";
        } else {
            sql = "SELECT song_id, title, genre_id FROM song WHERE LOWER(title) LIKE LOWER(?)";
        }

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
            }

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

    @Override
    public List<ArtistAccount> searchArtists(String keyword) {
        List<ArtistAccount> list = new ArrayList<>();
        String sql = (keyword == null || keyword.trim().isEmpty())
                ? "SELECT artist_id, stage_name FROM artist_account"
                : "SELECT artist_id, stage_name FROM artist_account WHERE LOWER(stage_name) LIKE LOWER(?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArtistAccount a = new ArtistAccount();
                a.setArtistId(rs.getInt("artist_id"));
                a.setStageName(rs.getString("stage_name"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Album> searchAlbums(String keyword) {
        List<Album> list = new ArrayList<>();
        String sql = (keyword == null || keyword.trim().isEmpty())
                ? "SELECT album_id, title FROM album"
                : "SELECT album_id, title FROM album WHERE LOWER(title) LIKE LOWER(?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
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

    @Override
    public List<Song> browseByGenre(int genreId) {
        return browse("SELECT song_id, title, genre_id FROM song WHERE genre_id=?", genreId);
    }

    @Override
    public List<Song> browseByArtist(int artistId) {
        return browse("SELECT song_id, title, genre_id FROM song WHERE artist_id=?", artistId);
    }

    @Override
    public List<Song> browseByAlbum(int albumId) {
        return browse("SELECT song_id, title, genre_id FROM song WHERE album_id=?", albumId);
    }

    private List<Song> browse(String sql, int id) {
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
