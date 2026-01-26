package com.revplay.dao;

import com.revplay.model.PodcastEpisode;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PodcastEpisodeDaoImpl implements IPodcastEpisodeDao {

    public void addEpisode(PodcastEpisode e) {
        String sql = "INSERT INTO podcast_episode (podcast_id, title, duration_seconds, release_date, file_url, play_count, created_at) VALUES (?, ?, ?, ?, ?, 0, ?)";
        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, e.getPodcastId());
            ps.setString(2, e.getTitle());
            ps.setInt(3, e.getDurationSeconds());
            ps.setDate(4, Date.valueOf(e.getReleaseDate()));
            ps.setString(5, e.getFileUrl());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
            System.out.println("✅ Episode added");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<PodcastEpisode> getEpisodesByPodcast(int podcastId) {
        List<PodcastEpisode> list = new ArrayList<>();
        String sql = "SELECT * FROM podcast_episode WHERE podcast_id=?";

        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, podcastId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new PodcastEpisode(
                        rs.getInt("episode_id"),
                        rs.getInt("podcast_id"),
                        rs.getString("title"),
                        rs.getInt("duration_seconds"),
                        rs.getDate("release_date").toLocalDate(),
                        rs.getString("file_url"),
                        rs.getInt("play_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void playEpisode(int id) {
        incrementPlayCount(id);
        System.out.println("▶️ Playing episode...");
    }

    public void incrementPlayCount(int id) {
        String sql = "UPDATE podcast_episode SET play_count = play_count + 1 WHERE episode_id=?";
        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<PodcastEpisode> searchEpisodesByPodcastTitle(String title) {
        List<PodcastEpisode> list = new ArrayList<>();
        String sql = """
            SELECT pe.*
            FROM podcast_episode pe
            JOIN podcast p ON pe.podcast_id = p.podcast_id
            WHERE LOWER(p.title) LIKE LOWER(?)
            """;

        try (PreparedStatement ps = JDBCUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new PodcastEpisode(
                        rs.getInt("episode_id"),
                        rs.getInt("podcast_id"),
                        rs.getString("title"),
                        rs.getInt("duration_seconds"),
                        rs.getDate("release_date").toLocalDate(),
                        rs.getString("file_url"),
                        rs.getInt("play_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
