package com.revplay.dao;

import com.revplay.model.UserAccount;

public interface UserDAO {

    boolean saveUser(UserAccount user);

    UserAccount findByEmail(String email);

    boolean updatePassword(String email, String newPassword);
}
