package com.revplay.dao;

import com.revplay.model.UserAccount;
import com.revplay.util.JDBCUtil;

import java.sql.Connection;
import java.util.List;

public class UserAccountDaoImpl implements IUserAccountDao {


    @Override
    public boolean addUserAccount(UserAccount userAccount) {
        Connection con= JDBCUtil.getConnection();
        return false;
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
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
        return List.of();
    }
}
