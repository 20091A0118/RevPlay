package com.revplay.dao;

import com.revplay.model.PodcastEpisode;
import java.util.List;

public interface IPodcastEpisodeDao {

    void createEpisode(PodcastEpisode episode);

    void updateEpisode(PodcastEpisode episode);

    void deleteEpisode(int episodeId);

    List<PodcastEpisode> getEpisodesByPodcast(int podcastId);
}
