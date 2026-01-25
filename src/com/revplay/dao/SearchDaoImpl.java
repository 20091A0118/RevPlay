package com.revplay.dao;

import com.revplay.model.*;
import com.revplay.util.JDBCUtil;
import java.sql.*;
import java.util.*;

public class SearchDaoImpl implements ISearchDao {

    public List<Song> searchSongs(String keyword) {
        return getSongs("SELECT song_id, title, genre_id FROM song WHERE LOWER(title) LIKE LOWER(?)",
                "%" + keyword + "%");
    }

    public List<Artist> searchArtists(String keyword) {
        List<Artist> list = new ArrayList<>();
        String sql = "SELECT artist_id, stage_name FROM artist_account WHERE LOWER(stage_name) LIKE LOWER(?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new Artist(rs.getInt(1), rs.getString(2)));

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    public List<Album> searchAlbums(String keyword) {
        List<Album> list = new ArrayList<>();
        String sql = "SELECT album_id, title FROM album WHERE LOWER(title) LIKE LOWER(?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new Album(rs.getInt(1), rs.getString(2)));

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    public List<Podcast> searchPodcasts(String keyword) {
        List<Podcast> list = new ArrayList<>();
        String sql = "SELECT podcast_id, title FROM podcast WHERE LOWER(title) LIKE LOWER(?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(new Podcast(rs.getInt(1), rs.getString(2)));

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    public List<Song> browseByGenre(int genreId) {
        return getSongs("SELECT song_id, title, genre_id FROM song WHERE genre_id=?", genreId);
    }

    public List<Song> browseByArtist(int artistId) {
        return getSongs("SELECT song_id, title, genre_id FROM song WHERE artist_id=?", artistId);
    }

    public List<Song> browseByAlbum(int albumId) {
        return getSongs("SELECT song_id, title, genre_id FROM song WHERE album_id=?", albumId);
    }

    private List<Song> getSongs(String sql, Object param) {
        List<Song> list = new ArrayList<>();

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, param);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                list.add(new Song(rs.getInt(1), rs.getString(2), rs.getInt(3)));

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }
}
