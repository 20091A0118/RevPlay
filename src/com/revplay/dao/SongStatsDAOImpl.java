package com.revplay.dao;

import com.revplay.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SongStatsDAOImpl implements ISongStatsDAO {

    @Override
    public void viewStatsByArtist(int artistId) {

        String sql = "SELECT s.song_id, s.title, st.play_count, st.favorite_count " +
                "FROM songs s JOIN song_stats st ON s.song_id = st.song_id " +
                "WHERE s.artist_id=? ORDER BY s.song_id";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--------- SONG STATS ---------");
            System.out.printf("%-10s %-25s %-10s %-10s%n", "SongID", "Title", "Plays", "Favs");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.printf("%-10d %-25s %-10d %-10d%n",
                        rs.getInt("song_id"),
                        rs.getString("title"),
                        rs.getInt("play_count"),
                        rs.getInt("favorite_count"));
            }

            if (!found) {
                System.out.println("No stats found!");
            }

        } catch (Exception e) {
            System.out.println("View Stats Error: " + e.getMessage());
        }
    }
}

