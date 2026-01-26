package com.revplay.service;

import com.revplay.model.Podcast;
import java.util.List;

public interface IPodcastService {
    void createPodcast(Podcast podcast);
    void updatePodcast(Podcast podcast);
    void deletePodcast(int podcastId);
    List<Podcast> getAllPodcasts();
}

