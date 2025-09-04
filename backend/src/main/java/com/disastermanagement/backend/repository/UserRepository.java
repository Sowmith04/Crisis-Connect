package com.disastermanagement.backend.repository;

import com.disastermanagement.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Find user by email (useful for login)
    Optional<User> findByEmail(String email);

    // Find users by role (e.g., "ADMIN" or "USER")
    java.util.List<User> findByRole(String role);
}
