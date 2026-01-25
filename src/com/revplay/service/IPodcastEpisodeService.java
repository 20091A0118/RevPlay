package com.revplay.service;

import com.revplay.model.PodcastEpisode;
import java.util.List;

public interface IPodcastEpisodeService {

    void addEpisode(PodcastEpisode episode);

    List<PodcastEpisode> viewEpisodesByPodcast(int podcastId);

    void playEpisode(int episodeId);
}
