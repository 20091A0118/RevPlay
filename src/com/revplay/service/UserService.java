package com.revplay.service;

import com.revplay.model.UserAccount;

public interface UserService {

    boolean registerUser(UserAccount user);

    UserAccount login(String email, String password);

    boolean changePassword(String email, String oldPassword, String newPassword);

    String forgotPassword(String email, String securityAnswer);
}
