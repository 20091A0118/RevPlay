package com.revplay.dao;

import com.revplay.model.Podcast;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PodcastDaoImpl implements IPodcastDao {

    private Connection con = JDBCUtil.getConnection();

    // ✅ CREATE (DO NOT INSERT podcast_id)
    @Override
    public void createPodcast(Podcast podcast) {
        String sql = """
            INSERT INTO podcast
            (title, host_name, category, description, created_at)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, podcast.getTitle());
            ps.setString(2, podcast.getHostName());
            ps.setString(3, podcast.getCategory());
            ps.setString(4, podcast.getDescription());
            ps.setTimestamp(5, Timestamp.valueOf(podcast.getCreatedAt()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ UPDATE
    @Override
    public void updatePodcast(Podcast podcast) {
        String sql = """
            UPDATE podcast
            SET title = ?, host_name = ?, category = ?, description = ?
            WHERE podcast_id = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, podcast.getTitle());
            ps.setString(2, podcast.getHostName());
            ps.setString(3, podcast.getCategory());
            ps.setString(4, podcast.getDescription());
            ps.setInt(5, podcast.getPodcastId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ DELETE
    @Override
    public void deletePodcast(int podcastId) {
        String sql = "DELETE FROM podcast WHERE podcast_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, podcastId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ READ
    @Override
    public List<Podcast> getAllPodcasts() {
        List<Podcast> list = new ArrayList<>();
        String sql = "SELECT * FROM podcast ORDER BY podcast_id";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Podcast p = new Podcast(
                        rs.getInt("podcast_id"),
                        rs.getString("title"),
                        rs.getString("host_name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
