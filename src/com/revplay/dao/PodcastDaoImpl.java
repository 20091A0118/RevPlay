package com.revplay.dao;

import com.revplay.model.Podcast;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PodcastDaoImpl implements IPodcastDao {

    @Override
    public void createPodcast(Podcast p) {
        String sql = "INSERT INTO podcast(title, host_name, category, description, created_at) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getHostName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getDescription());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
            System.out.println("✅ Podcast added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePodcast(Podcast p) {
        String sql = "UPDATE podcast SET title=?, host_name=?, category=?, description=? WHERE podcast_id=?";
        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getHostName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getDescription());
            ps.setInt(5, p.getPodcastId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Podcast updated successfully!");
                // Optional: Update podcast title in episodes table (if you store it redundantly)
                String epSql = "UPDATE podcast_episode SET title = ? WHERE podcast_id = ?";
                try (PreparedStatement psEp = JDBCUtil.getConnection().prepareStatement(epSql)) {
                    psEp.setString(1, p.getTitle());
                    psEp.setInt(2, p.getPodcastId());
                    psEp.executeUpdate();
                }
            } else {
                System.out.println("❌ Podcast ID not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePodcast(int podcastId) {
        String sql = "DELETE FROM podcast WHERE podcast_id=?";
        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, podcastId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Podcast deleted successfully!");
            } else {
                System.out.println("❌ Podcast ID not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Podcast> getAllPodcasts() {
        List<Podcast> list = new ArrayList<>();
        String sql = "SELECT * FROM podcast ORDER BY podcast_id";
        try (Statement st = JDBCUtil.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
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
