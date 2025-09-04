package com.disastermanagement.backend.service.impl;

import com.disastermanagement.backend.model.DisasterReport;
import com.disastermanagement.backend.model.User;
import com.disastermanagement.backend.repository.DisasterReportRepository;
import com.disastermanagement.backend.repository.UserRepository;
import com.disastermanagement.backend.service.DisasterReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisasterReportServiceImpl implements DisasterReportService {

    private final DisasterReportRepository reportRepository;
    private final UserRepository userRepository;

    public DisasterReportServiceImpl(DisasterReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DisasterReport createReport(DisasterReport report, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        report.setReportedBy(user);
        return reportRepository.save(report);
    }

    @Override
    public List<DisasterReport> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public List<DisasterReport> getReportsByLocation(String location) {
        return reportRepository.findByLocation(location);
    }

    @Override
    public List<DisasterReport> getReportsBySeverity(String severity) {
        return reportRepository.findBySeverity(severity);
    }
}
