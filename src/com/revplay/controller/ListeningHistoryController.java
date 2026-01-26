package com.revplay.controller;

import com.revplay.model.ListeningHistory;
import com.revplay.service.*;

public class ListeningHistoryController {

    private IListeningHistoryService service = new ListeningHistoryServiceImpl();

    public void play(int userId, int songId) {
        save(userId, songId, "PLAY");
        System.out.println("▶️ Playing song " + songId);
    }

    public void pause(int userId, int songId) {
        save(userId, songId, "PAUSE");
        System.out.println("⏸️ Paused song " + songId);
    }

    public void skip(int userId, int songId) {
        save(userId, songId, "SKIP");
        System.out.println("⏭️ Skipped song " + songId);
    }

    private void save(int userId, int songId, String action) {
        ListeningHistory h = new ListeningHistory();
        h.setUserId(userId);
        h.setSongId(songId);
        h.setActionType(action);
        service.add(h);
    }

    public void recent(int userId) {
        service.recent(userId).forEach(h ->
                System.out.println(
                        h.getSongId()+" | "+h.getActionType()+" | "+h.getPlayedAt()
                )
        );
    }
}
