package com.revplay.service;

import com.revplay.dao.IPodcastDao;
import com.revplay.dao.PodcastDaoImpl;
import com.revplay.model.Podcast;

import java.util.List;

public class PodcastServiceImpl {

    private IPodcastDao dao = new PodcastDaoImpl();

    public void createPodcast(Podcast podcast) {
        dao.createPodcast(podcast);
    }

    public void updatePodcast(Podcast podcast) {
        dao.updatePodcast(podcast);
    }

    public void deletePodcast(int podcastId) {
        dao.deletePodcast(podcastId);
    }

    public List<Podcast> viewAllPodcasts() {
        return dao.getAllPodcasts();
    }
}
