package com.revplay.dao;

import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class PlayListDAOImpl implements IPlayListDAO {

    // 1️⃣ CREATE PLAYLIST
    @Override
    public int createPlaylist(PlayList p) {

        String maxSql = "SELECT NVL(MAX(playlist_id), 0) FROM playlist";

        String insertSql =
                "INSERT INTO playlist " +
                        "(playlist_id, user_id, name, description, privacy_status, created_at, updated_at) " +
                        "VALUES (?, ?, ?, ?, ?, SYSTIMESTAMP, SYSTIMESTAMP)";

        try (Connection conn = JDBCUtil.getConnection()) {

            int newId = 1;

            // 1️⃣ Get MAX playlist_id
            try (PreparedStatement psMax = conn.prepareStatement(maxSql);
                 ResultSet rs = psMax.executeQuery()) {

                if (rs.next()) {
                    newId = rs.getInt(1) + 1;
                }
            }

            // 2️⃣ Insert playlist
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {

                ps.setInt(1, newId);                 // ✅ playlist_id
                ps.setInt(2, p.getUserId());         // user_id
                ps.setString(3, p.getName());        // name
                ps.setString(4, p.getDescription()); // description
                ps.setString(5, p.getPrivacyStatus());// privacy_status

                ps.executeUpdate();

                System.out.println("✅ Playlist created with ID: " + newId);
                return newId;
            }

        } catch (SQLException e) {
            System.out.println("Create playlist error: " + e.getMessage());
        }

        return 0;
    }


    //add playlist id and song id into  playlistsong table

    // 5️⃣ UPDATE PLAYLIST
    @Override
    public boolean updatePlaylist(PlayList p) {

        String sql =
                "UPDATE playlist SET name=?, description=?, privacy_status=?, updated_at=? " +
                        "WHERE playlist_id=?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getPrivacyStatus());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(5, p.getPlaylistId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update playlist error: " + e.getMessage());
            return false;
        }
    }

    // 6️⃣ DELETE PLAYLIST
    @Override
    public boolean deletePlaylist(int playlistId) {

        String deleteSongs =
                "DELETE FROM playlist_song WHERE playlist_id=?";
        String deletePlaylist =
                "DELETE FROM playlist WHERE playlist_id=?";

        try (Connection conn = JDBCUtil.getConnection()) {

            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(deleteSongs);
                 PreparedStatement ps2 = conn.prepareStatement(deletePlaylist)) {

                ps1.setInt(1, playlistId);
                ps1.executeUpdate();

                ps2.setInt(1, playlistId);
                boolean deleted = ps2.executeUpdate() > 0;

                conn.commit();
                return deleted;
            }

        } catch (SQLException e) {
            System.out.println("Delete playlist error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public PlayList getPlaylistById(int playlistId) {
        String sql = "SELECT * FROM playlist WHERE playlist_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PlayList p = new PlayList();
                    p.setPlaylistId(rs.getInt("playlist_id"));
                    p.setUserId(rs.getInt("user_id"));
                    p.setName(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    p.setPrivacyStatus(rs.getString("privacy_status"));
                    p.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    p.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                    return p;
                }
            }

        } catch (SQLException e) {
            System.out.println("Get playlist by ID error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<PlayList> getAllPlaylists() {
        List<PlayList> list = new ArrayList<>();
        String sql = "SELECT * FROM PLAYLIST ORDER BY PLAYLIST_ID";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PlayList p = new PlayList();
                p.setPlaylistId(rs.getInt("PLAYLIST_ID"));
                p.setUserId(rs.getInt("USER_ID"));
                p.setName(rs.getString("NAME"));
                p.setDescription(rs.getString("DESCRIPTION"));
                p.setPrivacyStatus(rs.getString("PRIVACY_STATUS"));
                Timestamp created = rs.getTimestamp("CREATED_AT");
                Timestamp updated = rs.getTimestamp("UPDATED_AT");
                if (created != null) p.setCreatedAt(created.toLocalDateTime());
                if (updated != null) p.setUpdatedAt(updated.toLocalDateTime());
                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Get all playlists error: " + e.getMessage());
        }

        return list;
    }

    //get all public playlist
    @Override
    public List<PlayList> getAllPublicPlaylists() {
        List<PlayList> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist WHERE UPPER(privacy_status)= 'PUBLIC' ORDER BY playlist_id";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PlayList p = new PlayList();
                p.setPlaylistId(rs.getInt("playlist_id"));
                p.setUserId(rs.getInt("user_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrivacyStatus(rs.getString("privacy_status"));
                p.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                p.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

                playlists.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching public playlists: " + e.getMessage());
        }

        return playlists;
    }

    @Override
    public boolean addSongToPlaylistSong(int playlistId, int songId) {
        String sql = "INSERT INTO playlist_song (playlist_id, song_id, added_at) VALUES (?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Add song error: " + e.getMessage());
            return false;
        }
    }



    //remove song
    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        String sql = "DELETE FROM playlist_song WHERE playlist_id = ? AND song_id = ?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Remove song error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public PlayListSong getSongFromPlaylistSong(int playlistId, int songId) {
        String sql = "SELECT playlist_id, song_id, added_at FROM playlist_song WHERE playlist_id=? AND song_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setInt(1, playlistId);
            psmt.setInt(2, songId);

            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return new PlayListSong(
                        rs.getInt("playlist_id"),
                        rs.getInt("song_id"),
                        rs.getTimestamp("added_at").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println("Get song error: " + e.getMessage());
        }
        return null;
    }
    @Override
    public boolean updateSongInPlaylist(int playlistId, int oldSongId, int newSongId) {
        String sql = "UPDATE playlist_song SET song_id = ? WHERE playlist_id = ? AND song_id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setInt(1, newSongId);
            psmt.setInt(2, playlistId);
            psmt.setInt(3, oldSongId);

            return psmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update song error: " + e.getMessage());
            return false;
        }
    }
    @Override
    public List<PlayListSong> getAllSongsByPlaylistId(int playlistId) {
        List<PlayListSong> songs = new ArrayList<>();
        String sql = "SELECT * FROM playlist_song WHERE playlist_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playlistId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int songId = rs.getInt("song_id");
                LocalDateTime addedAt = rs.getTimestamp("added_at").toLocalDateTime();
                songs.add(new PlayListSong(playlistId, songId, addedAt));
            }

        } catch (SQLException e) {
            System.out.println("Get songs by playlist error: " + e.getMessage());
        }
        return songs;
    }




}
