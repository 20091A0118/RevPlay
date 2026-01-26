package com.revplay.dao;

import com.revplay.model.Song;
import com.revplay.util.JDBCUtil;
import java.sql.*;
import java.util.*;

public class FavoriteDaoImpl implements IFavoriteDao {

    @Override
    public boolean addFavorite(int userId, int songId) {
        String sql = "INSERT INTO favorite_song (user_id, song_id, favorited_at) VALUES (?, ?, SYSDATE)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, songId);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean removeFavorite(int userId, int songId) {
        String sql = "DELETE FROM favorite_song WHERE user_id=? AND song_id=?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, songId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Song> getFavorites(int userId) {
        List<Song> list = new ArrayList<>();
        String sql = """
            SELECT s.song_id, s.title, s.genre_id
            FROM song s
            JOIN favorite_song f ON s.song_id=f.song_id
            WHERE f.user_id=?
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setSongId(rs.getInt(1));
                s.setTitle(rs.getString(2));
                s.setGenreId(rs.getInt(3));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
