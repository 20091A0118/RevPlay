package com.revplay.dao;

import com.revplay.model.Song;
import com.revplay.util.JDBCUtil;
import java.sql.*;
import java.util.*;

public class FavoriteDaoImpl implements IFavoriteDao {

    public boolean addFavorite(int userId, int songId) {

        String check = "SELECT COUNT(*) FROM favorite_song WHERE user_id=? AND song_id=?";
        String insert = "INSERT INTO favorite_song (user_id, song_id, favorited_at) VALUES (?, ?, SYSDATE)";

        try (Connection con = JDBCUtil.getConnection()) {

            try (PreparedStatement ps = con.prepareStatement(check)) {
                ps.setInt(1, userId);
                ps.setInt(2, songId);
                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0) return false;
            }

            try (PreparedStatement ps = con.prepareStatement(insert)) {
                ps.setInt(1, userId);
                ps.setInt(2, songId);
                return ps.executeUpdate() > 0;
            }

        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<Song> getFavoriteSongs(int userId) {
        List<Song> list = new ArrayList<>();
        String sql = """
            SELECT s.song_id, s.title, s.genre_id
            FROM song s JOIN favorite_song f ON s.song_id=f.song_id
            WHERE f.user_id=?
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                list.add(new Song(rs.getInt(1), rs.getString(2), rs.getInt(3)));

        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
