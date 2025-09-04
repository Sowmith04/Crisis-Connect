package com.disastermanagement.backend.repository;

import com.disastermanagement.backend.model.DisasterReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisasterReportRepository extends JpaRepository<DisasterReport, Long> {
    List<DisasterReport> findByLocation(String location);
    List<DisasterReport> findBySeverity(String severity);
    List<DisasterReport> findByType(String type);
}

