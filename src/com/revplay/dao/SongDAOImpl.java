package com.revplay.dao;

import com.revplay.model.Song;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAOImpl implements ISongDAO {

    @Override
    public boolean uploadSong(Song song) {

        String sql = "INSERT INTO songs (artist_id, album_id, title, genre, duration_sec, release_date) " +
                "VALUES (?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, song.getArtistId());

            if (song.getAlbumId() == null) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, song.getAlbumId());
            }

            ps.setString(3, song.getTitle());
            ps.setString(4, song.getGenre());
            ps.setInt(5, song.getDurationSec());
            ps.setString(6, song.getReleaseDate());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int songId = keys.getInt(1);
                    insertSongStats(songId);
                }
                return true;
            }

        } catch (Exception e) {
            System.out.println("Upload Song Error: " + e.getMessage());
        }

        return false;
    }

    private void insertSongStats(int songId) {
        String sql = "INSERT INTO song_stats (song_id, play_count, favorite_count) VALUES (?, 0, 0)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, songId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Insert Stats Error: " + e.getMessage());
        }
    }

    @Override
    public List<Song> getSongsByArtist(int artistId) {

        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM songs WHERE artist_id=? ORDER BY song_id";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setSongId(rs.getInt("song_id"));
                s.setArtistId(rs.getInt("artist_id"));

                int aid = rs.getInt("album_id");
                if (rs.wasNull()) s.setAlbumId(null);
                else s.setAlbumId(aid);

                s.setTitle(rs.getString("title"));
                s.setGenre(rs.getString("genre"));
                s.setDurationSec(rs.getInt("duration_sec"));

                Date d = rs.getDate("release_date");
                s.setReleaseDate(d == null ? null : d.toString());

                songs.add(s);
            }

        } catch (Exception e) {
            System.out.println("View Songs Error: " + e.getMessage());
        }

        return songs;
    }

    @Override
    public boolean updateSong(Song song) {

        String sql = "UPDATE songs SET title=?, genre=?, duration_sec=?, release_date=TO_DATE(?, 'YYYY-MM-DD') " +
                "WHERE song_id=? AND artist_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, song.getTitle());
            ps.setString(2, song.getGenre());
            ps.setInt(3, song.getDurationSec());
            ps.setString(4, song.getReleaseDate());
            ps.setInt(5, song.getSongId());
            ps.setInt(6, song.getArtistId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Update Song Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteSong(int songId, int artistId) {

        String delStats = "DELETE FROM song_stats WHERE song_id=?";
        String delSong = "DELETE FROM songs WHERE song_id=? AND artist_id=?";

        try (Connection con = JDBCUtil.getConnection()) {

            try (PreparedStatement ps1 = con.prepareStatement(delStats)) {
                ps1.setInt(1, songId);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = con.prepareStatement(delSong)) {
                ps2.setInt(1, songId);
                ps2.setInt(2, artistId);
                return ps2.executeUpdate() > 0;
            }

        } catch (Exception e) {
            System.out.println("Delete Song Error: " + e.getMessage());
            return false;
        }
    }
}
