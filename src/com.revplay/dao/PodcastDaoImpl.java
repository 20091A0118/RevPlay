package com.revplay.dao;

import com.revplay.model.Podcast;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PodcastDaoImpl implements IPodcastDao {

    public void createPodcast(Podcast p) {
        String sql = "INSERT INTO podcast(title, host_name, category, description, created_at) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getHostName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getDescription());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
            System.out.println("✅ Podcast added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Podcast> getAllPodcasts() {
        List<Podcast> list = new ArrayList<>();
        String sql = "SELECT * FROM podcast ORDER BY podcast_id";
        try (Statement st = JDBCUtil.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Podcast(
                        rs.getInt("podcast_id"),
                        rs.getString("title"),
                        rs.getString("host_name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updatePodcast(Podcast p) {
        String sql = "UPDATE podcast SET title=?, host_name=?, category=?, description=? WHERE podcast_id=?";
        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getTitle());
            ps.setString(2, p.getHostName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getDescription());
            ps.setInt(5, p.getPodcastId());
            ps.executeUpdate();
            System.out.println("✅ Podcast updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePodcast(int id) {
        String sql = "DELETE FROM podcast WHERE podcast_id=?";
        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("✅ Podcast deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Podcast> searchPodcastByTitle(String title) {
        List<Podcast> list = new ArrayList<>();
        String sql = "SELECT * FROM podcast WHERE LOWER(title) LIKE LOWER(?)";

        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Podcast(
                        rs.getInt("podcast_id"),
                        rs.getString("title"),
                        rs.getString("host_name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
