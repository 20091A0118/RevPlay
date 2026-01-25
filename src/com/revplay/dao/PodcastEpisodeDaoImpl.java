package com.revplay.dao;

import com.revplay.model.PodcastEpisode;
import com.revplay.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PodcastEpisodeDaoImpl implements IPodcastEpisodeDao {

    @Override
    public void createEpisode(PodcastEpisode e) {
        String sql = "INSERT INTO podcast_episodes VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, e.getEpisodeId());
            ps.setInt(2, e.getPodcastId());
            ps.setString(3, e.getTitle());
            ps.setInt(4, e.getDurationSeconds());
            ps.setDate(5, Date.valueOf(e.getReleaseDate()));
            ps.setString(6, e.getFileUrl());
            ps.setInt(7, e.getPlayCount());
            ps.setTimestamp(8, Timestamp.valueOf(e.getCreatedAt()));

            ps.executeUpdate();
            System.out.println("Episode created");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateEpisode(PodcastEpisode e) {
        String sql = """
            UPDATE podcast_episodes
            SET title=?, duration_seconds=?, file_url=?
            WHERE episode_id=?
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getTitle());
            ps.setInt(2, e.getDurationSeconds());
            ps.setString(3, e.getFileUrl());
            ps.setInt(4, e.getEpisodeId());

            ps.executeUpdate();
            System.out.println("Episode updated");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEpisode(int episodeId) {
        String sql = "DELETE FROM podcast_episodes WHERE episode_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, episodeId);
            ps.executeUpdate();
            System.out.println("Episode deleted");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<PodcastEpisode> getEpisodesByPodcast(int podcastId) {
        List<PodcastEpisode> list = new ArrayList<>();
        String sql = "SELECT * FROM podcast_episodes WHERE podcast_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

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
}
