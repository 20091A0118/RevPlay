package com.revplay.dao;

import com.revplay.model.Song;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAOImpl implements ISongDAO {

    @Override
    public boolean uploadSong(Song song) {

        String sql = "INSERT INTO songs (artist_id, album_id, title, genre, duration_sec, release_date, singer) " +
                "VALUES (?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, new String[]{"song_id"})) {

            if (con == null) {
                System.out.println("Upload Song Error: DB connection is null");
                return false;
            }

            ps.setInt(1, song.getArtistId());

            if (song.getAlbumId() == null) {
                ps.setNull(2, Types.NUMERIC);
            } else {
                ps.setInt(2, song.getAlbumId());
            }

            ps.setString(3, song.getTitle());
            ps.setString(4, song.getGenre());
            ps.setInt(5, song.getDurationSec());
            ps.setString(6, song.getReleaseDate());
            ps.setString(7, song.getSinger());

            int rows = ps.executeUpdate();
            if (rows <= 0) return false;

            // ✅ Insert stats automatically
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                int songId = keys.getInt(1);

                String statsSql = "INSERT INTO song_stats (song_id, play_count, favorite_count) VALUES (?, 0, 0)";
                try (PreparedStatement ps2 = con.prepareStatement(statsSql)) {
                    ps2.setInt(1, songId);
                    ps2.executeUpdate();
                }
            }

            return true;

        } catch (Exception e) {
            System.out.println("Upload Song Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Song> getSongsByArtist(int artistId) {

        List<Song> songs = new ArrayList<>();

        String sql = "SELECT song_id, artist_id, album_id, title, genre, duration_sec, singer, " +
                "TO_CHAR(release_date, 'YYYY-MM-DD') AS release_date " +
                "FROM songs WHERE artist_id=? ORDER BY song_id";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("Get Songs Error: DB connection is null");
                return songs;
            }

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                songs.add(mapSong(rs));
            }

        } catch (Exception e) {
            System.out.println("Get Songs Error: " + e.getMessage());
        }

        return songs;
    }

    @Override
    public List<Song> getSongsByAlbum(int albumId) {

        List<Song> songs = new ArrayList<>();

        String sql = "SELECT song_id, artist_id, album_id, title, genre, duration_sec, singer, " +
                "TO_CHAR(release_date, 'YYYY-MM-DD') AS release_date " +
                "FROM songs WHERE album_id=? ORDER BY song_id";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("Get Songs By Album Error: DB connection is null");
                return songs;
            }

            ps.setInt(1, albumId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                songs.add(mapSong(rs));
            }

        } catch (Exception e) {
            System.out.println("Get Songs By Album Error: " + e.getMessage());
        }

        return songs;
    }

    @Override
    public List<Song> searchSongsByTitle(String keyword) {

        List<Song> songs = new ArrayList<>();

        String sql = "SELECT song_id, artist_id, album_id, title, genre, duration_sec, singer, " +
                "TO_CHAR(release_date, 'YYYY-MM-DD') AS release_date " +
                "FROM songs WHERE LOWER(title) LIKE LOWER(?) ORDER BY song_id";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("Search Songs Error: DB connection is null");
                return songs;
            }

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                songs.add(mapSong(rs));
            }

        } catch (Exception e) {
            System.out.println("Search Songs Error: " + e.getMessage());
        }

        return songs;
    }

    @Override
    public boolean updateSong(Song song) {

        String sql = "UPDATE songs SET title=?, genre=?, duration_sec=?, " +
                "release_date=TO_DATE(?, 'YYYY-MM-DD'), singer=? " +
                "WHERE song_id=? AND artist_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (con == null) {
                System.out.println("Update Song Error: DB connection is null");
                return false;
            }

            ps.setString(1, song.getTitle());
            ps.setString(2, song.getGenre());
            ps.setInt(3, song.getDurationSec());
            ps.setString(4, song.getReleaseDate());
            ps.setString(5, song.getSinger());
            ps.setInt(6, song.getSongId());
            ps.setInt(7, song.getArtistId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Update Song Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteSong(int songId, int artistId) {

        String deleteStats = "DELETE FROM song_stats WHERE song_id=?";
        String deleteSong = "DELETE FROM songs WHERE song_id=? AND artist_id=?";

        try (Connection con = JDBCUtil.getConnection()) {

            if (con == null) {
                System.out.println("Delete Song Error: DB connection is null");
                return false;
            }

            try (PreparedStatement ps1 = con.prepareStatement(deleteStats)) {
                ps1.setInt(1, songId);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = con.prepareStatement(deleteSong)) {
                ps2.setInt(1, songId);
                ps2.setInt(2, artistId);
                return ps2.executeUpdate() > 0;
            }

        } catch (Exception e) {
            System.out.println("Delete Song Error: " + e.getMessage());
            return false;
        }
    }

    private Song mapSong(ResultSet rs) throws Exception {

        Song s = new Song();

        s.setSongId(rs.getInt("song_id"));
        s.setArtistId(rs.getInt("artist_id"));

        int alb = rs.getInt("album_id");
        if (rs.wasNull()) s.setAlbumId(null);
        else s.setAlbumId(alb);

        s.setTitle(rs.getString("title"));
        s.setGenre(rs.getString("genre"));
        s.setDurationSec(rs.getInt("duration_sec"));
        s.setSinger(rs.getString("singer"));
        s.setReleaseDate(rs.getString("release_date"));

        return s;
    }
}
