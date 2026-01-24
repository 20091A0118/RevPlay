package com.revplay.auth.service;

import com.revplay.auth.model.User;

public interface UserService {

    boolean registerUser(User user);

    User login(String email, String password);

    boolean changePassword(String email, String oldPassword, String newPassword);

    String forgotPassword(String email, String securityAnswer);
}
