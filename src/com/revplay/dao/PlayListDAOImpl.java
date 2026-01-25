
package com.revplay.dao;

import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayListDAOImpl implements IPlayListDAO {

    // ---------------- GENERATE NEW PLAYLIST ID ----------------
    private int generatePlaylistId() {
        String sql = "SELECT NVL(MAX(playlist_id), 0) + 1 AS new_id FROM playlist";
        try (Connection conn = JDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("new_id");
            }

        } catch (SQLException e) {
            System.out.println("Error generating playlist ID: " + e.getMessage());
        }
        return 1; // fallback to 1 if table is empty or error occurs
    }

    // ---------------- CREATE PLAYLIST ----------------
    @Override
    public boolean createPlayList(PlayList playlist) {
        String sql = "INSERT INTO playlist (playlist_id, user_id, name, description, privacy_status, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        int newId = generatePlaylistId();
        playlist.setPlaylistId(newId); // set the generated ID to the object

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlist.getPlaylistId());
            ps.setInt(2, playlist.getUserId());
            ps.setString(3, playlist.getName());
            ps.setString(4, playlist.getDescription());
            ps.setString(5, playlist.getPrivacyStatus());
            ps.setTimestamp(6, Timestamp.valueOf(playlist.getCreatedAt()));
            ps.setTimestamp(7, Timestamp.valueOf(playlist.getUpdatedAt()));

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error creating playlist: " + e.getMessage());
            return false;
        }
    }

    // ---------------- UPDATE PLAYLIST ----------------
    @Override
    public boolean updatePlayList(PlayList playlist) {
        String sql = "UPDATE playlist SET name = ?, description = ?, privacy_status = ?, updated_at = ? " +
                "WHERE playlist_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, playlist.getName());
            ps.setString(2, playlist.getDescription());
            ps.setString(3, playlist.getPrivacyStatus());
            ps.setTimestamp(4, Timestamp.valueOf(playlist.getUpdatedAt()));
            ps.setInt(5, playlist.getPlaylistId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating playlist: " + e.getMessage());
            return false;
        }
    }

    // ---------------- DELETE PLAYLIST ----------------
    @Override
    public boolean deletePlayList(int playlistId) {
        String sql1 = "DELETE FROM playlist_song WHERE playlist_id = ?";
        String sql2 = "DELETE FROM playlist WHERE playlist_id = ?";

        try (Connection conn = JDBCUtil.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(sql1);
                 PreparedStatement ps2 = conn.prepareStatement(sql2)) {

                ps1.setInt(1, playlistId);
                ps1.executeUpdate();

                ps2.setInt(1, playlistId);
                ps2.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error deleting playlist: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.out.println("DB connection error: " + e.getMessage());
            return false;
        }
    }

    // ---------------- ADD SONG TO PLAYLIST ----------------
    @Override
    public boolean addSongToPlaylist(PlayListSong psong) {
        String sql = "INSERT INTO playlist_song (playlist_id, song_id, added_at) VALUES (?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, psong.getPlaylistId());
            ps.setInt(2, psong.getSongId());
            ps.setTimestamp(3, Timestamp.valueOf(psong.getAddedAt()));
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error adding song: " + e.getMessage());
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
            System.out.println("Error removing song: " + e.getMessage());
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
            System.out.println("Error fetching public playlists: " + e.getMessage());
        }

        return playlists;
    }
}

