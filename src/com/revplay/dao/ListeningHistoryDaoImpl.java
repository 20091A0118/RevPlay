package com.revplay.dao;

import com.revplay.model.ListeningHistory;
import com.revplay.util.JDBCUtil;
import java.sql.*;
import java.util.*;

public class ListeningHistoryDaoImpl implements IListeningHistoryDao {

    public boolean addHistory(ListeningHistory h) {
        String sql = "INSERT INTO listening_history (user_id,song_id,played_at,action_type) VALUES (?,?,SYSDATE,?)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, h.getUserId());
            ps.setInt(2, h.getSongId());
            ps.setString(3, h.getActionType());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public List<ListeningHistory> getRecent(int userId) {
        List<ListeningHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM listening_history WHERE user_id=? ORDER BY played_at DESC";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ListeningHistory h = new ListeningHistory();
                h.setHistoryId(rs.getInt("history_id"));
                h.setUserId(rs.getInt("user_id"));
                h.setSongId(rs.getInt("song_id"));
                h.setPlayedAt(rs.getTimestamp("played_at").toLocalDateTime());
                h.setActionType(rs.getString("action_type"));
                list.add(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
