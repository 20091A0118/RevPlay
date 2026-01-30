package com.revplay.dao;

import com.revplay.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeUserAccountDaoTest {

    private FakeUserAccountDao dao;

    @BeforeEach
    public void setup() {
        dao = new FakeUserAccountDao();
    }

    @Test
    public void testAddUserAccount() {
        UserAccount user = new UserAccount();
        user.setEmail("test@test.com");
        user.setPasswordHash("pass");

        boolean result = dao.addUserAccount(user);
        assertTrue(result);
        assertNotNull(dao.getUserAccountByEmail("test@test.com"));
    }

    @Test
    public void testAddDuplicateUser() {
        UserAccount user1 = new UserAccount();
        user1.setEmail("same@test.com");
        dao.addUserAccount(user1);

        UserAccount user2 = new UserAccount();
        user2.setEmail("same@test.com");

        boolean result = dao.addUserAccount(user2);
        assertFalse(result, "Should not allow duplicate emails");
    }
}
