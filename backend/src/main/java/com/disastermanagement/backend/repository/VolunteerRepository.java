package com.disastermanagement.backend.repository;

import com.disastermanagement.backend.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}

