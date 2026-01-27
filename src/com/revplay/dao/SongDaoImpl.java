package com.revplay.dao;

import com.revplay.model.Song;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDaoImpl implements ISongDao {

    @Override
    public void addSong(Song song) {

        String sql = "INSERT INTO song (title, album_id, genre_id, duration) VALUES (?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getAlbumId());
            ps.setInt(3, song.getGenreId());
            ps.setInt(4, song.getDuration());

            ps.executeUpdate();
            System.out.println("Song added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Song> getSongsByAlbum(int albumId) {

        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM song WHERE album_id = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, albumId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setSongId(rs.getInt("song_id"));
                s.setTitle(rs.getString("title"));
                s.setAlbumId(rs.getInt("album_id"));
                s.setGenreId(rs.getInt("genre_id"));
                s.setDuration(rs.getInt("duration"));
                s.setPlayCount(rs.getInt("play_count"));
                songs.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    @Override
    public void updatePlayCount(int songId) {

        String sql = "UPDATE song SET play_count = play_count + 1 WHERE song_id = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, songId);
            ps.executeUpdate();
            System.out.println("Play count updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

