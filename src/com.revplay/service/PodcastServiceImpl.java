package com.revplay.service;

import com.revplay.dao.*;
import com.revplay.model.Podcast;
import java.util.List;

public class PodcastServiceImpl {

    private IPodcastDao dao = new PodcastDaoImpl();

    public void createPodcast(Podcast p) { dao.createPodcast(p); }
    public void updatePodcast(Podcast p) { dao.updatePodcast(p); }
    public void deletePodcast(int id) { dao.deletePodcast(id); }
    public List<Podcast> getAllPodcasts() { return dao.getAllPodcasts(); }
    public List<Podcast> searchPodcastByTitle(String title) {
        return dao.searchPodcastByTitle(title);
    }
}
