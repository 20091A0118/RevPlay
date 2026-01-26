package com.revplay.dao;

import com.revplay.model.Podcast;
import java.util.List;

public interface IPodcastDao {
    void createPodcast(Podcast podcast);
    List<Podcast> getAllPodcasts();
    void updatePodcast(Podcast podcast);
    void deletePodcast(int podcastId);

    List<Podcast> searchPodcastByTitle(String title);
}
