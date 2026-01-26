package com.revplay.service;

import com.revplay.dao.UserDAOImpl;
import com.revplay.model.UserAccount;

public class UserServiceImpl implements UserService {

    private final UserDAOImpl dao = new UserDAOImpl();

    @Override
    public boolean registerUser(UserAccount user) {
        if (dao.findByEmail(user.getEmail()) != null) {
            return false;
        }
        return dao.saveUser(user);
    }

    @Override
    public UserAccount login(String email, String password) {
        UserAccount user = dao.findByEmail(email);
        if (user != null && user.getPasswordHash().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean changePassword(String email, String oldPass, String newPass) {
        UserAccount user = dao.findByEmail(email);
        if (user != null && user.getPasswordHash().equals(oldPass)) {
            return dao.updatePassword(email, newPass);
        }
        return false;
    }

    @Override
    public String forgotPassword(String email, String answer) {
        UserAccount user = dao.findByEmail(email);
        if (user != null && user.getSecurityAnswer().equals(answer)) {
            return user.getPasswordHash();
        }
        return null;
    }
}
