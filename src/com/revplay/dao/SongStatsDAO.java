package com.revplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongStatsDAO {

    public void viewStatsByArtist(int artistId) {
        String sql = "SELECT s.song_id, s.title, st.play_count, st.favorite_count " +
                "FROM songs s " +
                "JOIN song_stats st ON s.song_id = st.song_id " +
                "WHERE s.artist_id=? " +
                "ORDER BY s.song_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n---------- SONG STATS ----------");
            System.out.printf("%-10s %-25s %-12s %-12s%n", "SongID", "Title", "Plays", "Favorites");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-10d %-25s %-12d %-12d%n",
                        rs.getInt("song_id"),
                        rs.getString("title"),
                        rs.getInt("play_count"),
                        rs.getInt("favorite_count"));
            }

            if (!found) {
                System.out.println("No stats found!");
            }

        } catch (SQLException e) {
            System.out.println("View stats failed: " + e.getMessage());
        }
    }
}

