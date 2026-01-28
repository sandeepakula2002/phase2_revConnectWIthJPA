package com.revconnect.service;

import com.revconnect.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testRegisterAndLogin() {
        // Register a new user
        User user = new User("test@example.com", "password123", "PERSONAL");
        User savedUser = userService.register(user);

        assertNotNull(savedUser.getId());
        assertEquals("test@example.com", savedUser.getEmail());

        // Login with correct credentials
        Optional<User> loggedInUser = userService.login("test@example.com", "password123");
        assertTrue(loggedInUser.isPresent());
        assertEquals("test@example.com", loggedInUser.get().getEmail());

        // Login with wrong password
        Optional<User> wrongPassword = userService.login("test@example.com", "wrong");
        assertFalse(wrongPassword.isPresent());
    }

    @Test
    void testDuplicateEmail() {
        User user1 = new User("duplicate@example.com", "pass1", "PERSONAL");
        userService.register(user1);

        User user2 = new User("duplicate@example.com", "pass2", "CREATOR");

        assertThrows(RuntimeException.class, () -> {
            userService.register(user2);
        });
    }
}