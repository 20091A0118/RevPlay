package com.revplay.auth.service;

import com.revplay.auth.dao.UserDAO;
import com.revplay.auth.model.User;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAO();

    @Override
    public boolean registerUser(User user) {
        User existingUser = userDAO.findByEmail(user.getEmail());
        if (existingUser != null) {
            return false;
        }
        return userDAO.saveUser(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {

        User user = userDAO.findByEmail(email);
        if (user != null && user.getPassword().equals(oldPassword)) {

            String sql = "UPDATE users SET password = ? WHERE email = ?";

            try (var con = com.revplay.auth.util.DBConnection.getConnection();
                 var ps = con.prepareStatement(sql)) {

                ps.setString(1, newPassword);
                ps.setString(2, email);
                ps.executeUpdate();
                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String forgotPassword(String email, String securityAnswer) {

        User user = userDAO.findByEmail(email);
        if (user != null && user.getSecurityAnswer().equals(securityAnswer)) {
            return user.getPassword();
        }
        return null;
    }
}