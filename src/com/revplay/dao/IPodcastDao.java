package com.revplay.dao;

import com.revplay.model.Podcast;
import java.util.List;

public interface IPodcastDao {

    void createPodcast(Podcast podcast);

    void updatePodcast(Podcast podcast);

    void deletePodcast(int podcastId);

    List<Podcast> getAllPodcasts();
}
