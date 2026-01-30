package com.revplay.dao;

import com.revplay.model.UserAccount;
import java.util.ArrayList;
import java.util.List;

public class FakeUserAccountDao implements IUserAccountDao {

    private List<UserAccount> users = new ArrayList<>();

    @Override
    public boolean addUserAccount(UserAccount user) {
        if (getUserAccountByEmail(user.getEmail()) != null)
            return false;
        users.add(user);
        return true;
    }

    @Override
    public UserAccount getUserAccountByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    // Unused methods for basic service testing
    @Override
    public boolean updateUserAccount(UserAccount user) {
        return false;
    }

    @Override
    public boolean deleteUserAccount(int userId) {
        return false;
    }

    @Override
    public UserAccount getUserAccount(int userId) {
        return null;
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return users;
    }
}
