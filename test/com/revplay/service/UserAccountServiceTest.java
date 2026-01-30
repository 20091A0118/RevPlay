package com.revplay.service;

import com.revplay.dao.FakeUserAccountDao;
import com.revplay.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAccountServiceTest {

    private UserAccountServiceImpl userService;
    private FakeUserAccountDao fakeDao;

    @BeforeEach
    public void setup() {
        userService = new UserAccountServiceImpl();
        fakeDao = new FakeUserAccountDao();
        userService.setUserDao(fakeDao);
    }

    @Test
    public void testRegisterUser_Success() {
        UserAccount user = new UserAccount();
        user.setEmail("test@example.com");
        user.setPasswordHash("password");
        user.setFullName("Test User");

        boolean result = userService.addUserAccount(user);
        assertTrue(result, "User should be registered successfully");
        assertNotNull(userService.getUserByEmail("test@example.com"));
    }

    @Test
    public void testRegisterUser_DuplicateEmail() {
        UserAccount user1 = new UserAccount();
        user1.setEmail("duplicate@example.com");
        userService.addUserAccount(user1);

        UserAccount user2 = new UserAccount();
        user2.setEmail("duplicate@example.com");

        boolean result = userService.addUserAccount(user2);
        assertFalse(result, "Should not allow duplicate email registration");
    }

    @Test
    public void testGetUserByEmail_NotFound() {
        UserAccount user = userService.getUserByEmail("nonexistent@example.com");
        assertNull(user, "Should return null for non-existent user");
    }
}
