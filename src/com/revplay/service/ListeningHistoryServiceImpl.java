package com.revplay.service;

import com.revplay.dao.*;
import com.revplay.model.ListeningHistory;
import java.util.List;

public class ListeningHistoryServiceImpl implements IListeningHistoryService {

    private IListeningHistoryDao dao = new ListeningHistoryDaoImpl();

    public boolean add(ListeningHistory h) {
        return dao.addHistory(h);
    }

    public List<ListeningHistory> recent(int userId) {
        return dao.getRecent(userId);
    }
}
