package com.revplay.dao;

import com.revplay.model.PodcastEpisode;
import java.util.List;

public interface IPodcastEpisodeDao {
    void addEpisode(PodcastEpisode episode);
    List<PodcastEpisode> getEpisodesByPodcast(int podcastId);
    void playEpisode(int episodeId);
    void incrementPlayCount(int episodeId);

    List<PodcastEpisode> searchEpisodesByPodcastTitle(String title);
}
