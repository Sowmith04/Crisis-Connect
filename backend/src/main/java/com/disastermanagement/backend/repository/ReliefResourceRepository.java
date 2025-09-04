package com.disastermanagement.backend.repository;

import com.disastermanagement.backend.model.ReliefResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReliefResourceRepository extends JpaRepository<ReliefResource, Long> {
}

