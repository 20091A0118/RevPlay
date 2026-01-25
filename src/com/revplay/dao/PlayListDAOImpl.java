package com.revplay.dao;

import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public
class PlayListDAOImpl implements IPlayListDAO {

    // ---------------- CREATE PLAYLIST ----------------
    @Override
    public boolean createPlayList(PlayList playlist) {
        String sql = """
                    INSERT INTO playlist (user_id, name, description, privacy_status, created_at, updated_at)
                    VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"playlist_id"})) {

            ps.setInt(1, playlist.getUserId());
            ps.setString(2, playlist.getName());
            ps.setString(3, playlist.getDescription());
            ps.setString(4, playlist.getPrivacyStatus());
            ps.setTimestamp(5, Timestamp.valueOf(playlist.getCreatedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(playlist.getUpdatedAt()));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        playlist.setPlaylistId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error creating playlist: " + e.getMessage());
        }
        return false;
    }

    // ---------------- UPDATE PLAYLIST ----------------
    @Override
    public boolean updatePlayList(PlayList playlist) {
        String sql = """
                    UPDATE playlist
                    SET name = ?, description = ?, privacy_status = ?, updated_at = ?
                    WHERE playlist_id = ?
                """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, playlist.getName());
            ps.setString(2, playlist.getDescription());
            ps.setString(3, playlist.getPrivacyStatus());
            ps.setTimestamp(4, Timestamp.valueOf(playlist.getUpdatedAt()));
            ps.setInt(5, playlist.getPlaylistId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating playlist: " + e.getMessage());
            return false;
        }
    }

    // ---------------- DELETE PLAYLIST ----------------
    @Override
    public boolean deletePlayList(int playlistId) {
        String sqlDeletePlaylist = "DELETE FROM playlist WHERE playlist_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement psPlaylist = conn.prepareStatement(sqlDeletePlaylist)) {

            psPlaylist.setInt(1, playlistId);
            int rowsDeleted = psPlaylist.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Playlist deleted successfully!");
                return true;
            } else {
                System.out.println("Playlist not found!");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error deleting playlist: " + e.getMessage());
            return false;
        }
    }

    // ---------------- ADD SONG TO PLAYLIST ----------------
    @Override
    public boolean addSongToPlaylist(PlayListSong psong) {

        String checkPlaylistSql =
                "SELECT COUNT(*) FROM playlist WHERE playlist_id = ?";

        String insertSql =
                "INSERT INTO playlist_song (playlist_id, song_id, added_at) VALUES (?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection()) {

            // 1️ Check playlist exists
            try (PreparedStatement ps = conn.prepareStatement(checkPlaylistSql)) {
                ps.setInt(1, psong.getPlaylistId());
                ResultSet rs = ps.executeQuery();

                if (rs.next() && rs.getInt(1) == 0) {
                    System.out.println("❌ Playlist does not exist!");
                    return false;
                }
            }


            // 2️⃣ Insert into playlist_song
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setInt(1, psong.getPlaylistId());
                ps.setInt(2, psong.getSongId());
                ps.setTimestamp(3, Timestamp.valueOf(psong.getAddedAt()));
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error adding song: " + e.getMessage());
            return false;
        }
    }


    // ---------------- REMOVE SONG FROM PLAYLIST ----------------
    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        String sql = "DELETE FROM playlist_song WHERE playlist_id = ? AND song_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error removing song: " + e.getMessage());
            return false;
        }
    }

    // ---------------- VIEW PUBLIC PLAYLISTS ----------------
    @Override
    public List<PlayList> getPublicPlayLists() {
        List<PlayList> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist WHERE LOWER(privacy_status) = 'public'";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                playlists.add(new PlayList(
                        rs.getInt("playlist_id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("privacy_status"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching public playlists: " + e.getMessage());
        }

        return playlists;
    }

    // ---------------- GET PLAYLIST BY ID ----------------
    @Override
    public PlayList getPlayListById(int playlistId) {
        String sql = "SELECT * FROM playlist WHERE playlist_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PlayList(
                            rs.getInt("playlist_id"),
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("privacy_status"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getTimestamp("updated_at").toLocalDateTime()
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching playlist: " + e.getMessage());
        }

        return null;
    }

    // ---------------- GET SONGS IN PLAYLIST ----------------
    @Override
    public List<PlayListSong> getSongsInPlaylist(int playlistId) {
        List<PlayListSong> songs = new ArrayList<>();
        String sql = "SELECT * FROM playlist_song WHERE playlist_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songs.add(new PlayListSong(
                            rs.getInt("playlist_id"),
                            rs.getInt("song_id"),
                            rs.getTimestamp("added_at").toLocalDateTime()
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching songs in playlist: " + e.getMessage());
        }

        return songs;
    }


    // Example: fetch playlist ID by name
    @Override
    public int getPlaylistIdByName( String name) {
        String sql = "SELECT playlist_id FROM playlist WHERE name = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("playlist_id");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching playlist ID: " + e.getMessage());
        }
        return 0;
    }
}