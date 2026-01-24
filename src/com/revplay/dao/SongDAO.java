package com.revplay.dao;

import com.revplay.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    public boolean uploadSong(Song song) {
        String sql = "INSERT INTO songs (artist_id, album_id, title, genre, duration_sec, release_date) " +
                "VALUES (?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, song.getArtistId());

            if (song.getAlbumId() == null) ps.setNull(2, Types.INTEGER);
            else ps.setInt(2, song.getAlbumId());

            ps.setString(3, song.getTitle());
            ps.setString(4, song.getGenre());
            ps.setInt(5, song.getDurationSec());
            ps.setString(6, song.getReleaseDate());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int newSongId = keys.getInt(1);
                    insertSongStats(newSongId);
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Upload song failed: " + e.getMessage());
        }
        return false;
    }

    private void insertSongStats(int songId) {
        String sql = "INSERT INTO song_stats (song_id, play_count, favorite_count) VALUES (?, 0, 0)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, songId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Stats insert failed: " + e.getMessage());
        }
    }

    public List<Song> viewSongsByArtist(int artistId) {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM songs WHERE artist_id=? ORDER BY song_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setSongId(rs.getInt("song_id"));
                s.setArtistId(rs.getInt("artist_id"));

                int alb = rs.getInt("album_id");
                if (rs.wasNull()) s.setAlbumId(null);
                else s.setAlbumId(alb);

                s.setTitle(rs.getString("title"));
                s.setGenre(rs.getString("genre"));
                s.setDurationSec(rs.getInt("duration_sec"));

                Date d = rs.getDate("release_date");
                s.setReleaseDate(d == null ? null : d.toString());

                songs.add(s);
            }

        } catch (SQLException e) {
            System.out.println("View songs failed: " + e.getMessage());
        }
        return songs;
    }

    public boolean updateSong(int songId, int artistId, String title, String genre, int duration, String releaseDate) {
        String sql = "UPDATE songs SET title=?, genre=?, duration_sec=?, release_date=TO_DATE(?, 'YYYY-MM-DD') " +
                "WHERE song_id=? AND artist_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setInt(3, duration);
            ps.setString(4, releaseDate);
            ps.setInt(5, songId);
            ps.setInt(6, artistId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Update song failed: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteSong(int songId, int artistId) {
        String delStats = "DELETE FROM song_stats WHERE song_id=?";
        String delSong = "DELETE FROM songs WHERE song_id=? AND artist_id=?";

        try (Connection con = DBConnection.getConnection()) {

            try (PreparedStatement ps1 = con.prepareStatement(delStats)) {
                ps1.setInt(1, songId);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = con.prepareStatement(delSong)) {
                ps2.setInt(1, songId);
                ps2.setInt(2, artistId);
                int rows = ps2.executeUpdate();
                return rows > 0;
            }

        } catch (SQLException e) {
            System.out.println("Delete song failed: " + e.getMessage());
        }
        return false;
    }
}
