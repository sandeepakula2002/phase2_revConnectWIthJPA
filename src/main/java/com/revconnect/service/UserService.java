package com.revconnect.service;

import com.revconnect.models.User;
import com.revconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        logger.info("Attempting to register user: {}", user.getEmail());

        if (userRepository.existsByEmail(user.getEmail())) {
            logger.warn("Registration failed. Email already exists: {}", user.getEmail());
            throw new RuntimeException("Email already exists");
        }

        User savedUser = userRepository.save(user);
        logger.info("User registered successfully: {}", savedUser.getEmail());
        return savedUser;
    }

    public Optional<User> login(String email, String password) {
        logger.info("Login attempt for email: {}", email);

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            logger.info("Login successful for email: {}", email);
            return user;
        }

        logger.warn("Login failed for email: {}", email);
        return Optional.empty();
    }

    public Optional<User> getUserById(Long id) {
        logger.info("Fetching user by id: {}", id);
        return userRepository.findById(id);
    }
}
