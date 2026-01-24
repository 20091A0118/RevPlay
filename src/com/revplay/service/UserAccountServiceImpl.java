package com.revplay.service;

import com.revplay.dao.IUserAccountDao;
import com.revplay.dao.UserAccountDaoImpl;
import com.revplay.model.UserAccount;

import java.util.List;

public class UserAccountServiceImpl implements IUserAccountService{
    private static IUserAccountDao userAccountDao=new UserAccountDaoImpl();
    @Override
    public boolean addUserAccount(UserAccount userAccount) {

        return userAccountDao.addUserAccount(userAccount);
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
