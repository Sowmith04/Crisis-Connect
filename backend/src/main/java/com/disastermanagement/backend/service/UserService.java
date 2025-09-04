package com.disastermanagement.backend.service;

import com.disastermanagement.backend.model.User;
import com.disastermanagement.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // inject encoder from SecurityConfig

    /**
     * Register a new user with hashed password
     */
    public User register(User user) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    /**
     * Login user by email + password
     */
    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Check raw password against hashed one
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null; // or throw an exception for invalid credentials
    }
}


