package com.revconnect.service;

import com.revconnect.models.User;
import com.revconnect.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final Logger logger = LogManager.getLogger(UserServiceTest.class);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // ---------- Test register ----------
    @Test
    void testRegister() {
        logger.info("testRegister started");

        User user = new User(); // No-args constructor
        user.setEmail("sagar@example.com");
        user.setPassword("password123");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User saved = userService.register(user);

        assertEquals("sagar@example.com", saved.getEmail());
        assertEquals("password123", saved.getPassword());

        logger.info("testRegister success");
    }

    // ---------- Test getUserById ----------
    @Test
    void testGetUserById() {
        logger.info("testGetUserById started");

        Long userId = 1L;
        User mockUser = new User();
        mockUser.setEmail("sagar@example.com");
        mockUser.setPassword("password123");

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        Optional<User> user = userService.getUserById(userId);

        assertTrue(user.isPresent());
        assertEquals("sagar@example.com", user.get().getEmail());

        logger.info("testGetUserById success");
    }

    // ---------- Test login ----------
    @Test
    void testLoginSuccess() {
        logger.info("testLoginSuccess started");

        String email = "sagar@example.com";
        String password = "password123";

        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        Optional<User> user = userService.login(email, password);

        assertTrue(user.isPresent());
        assertEquals(email, user.get().getEmail());

        logger.info("testLoginSuccess success");
    }

    @Test
    void testLoginFailure() {
        logger.info("testLoginFailure started");

        String email = "wrong@example.com";
        String password = "wrongpass";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<User> user = userService.login(email, password);

        assertTrue(user.isEmpty());

        logger.info("testLoginFailure success");
    }
}
