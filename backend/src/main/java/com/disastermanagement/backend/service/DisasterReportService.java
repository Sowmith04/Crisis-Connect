package com.disastermanagement.backend.service;

import com.disastermanagement.backend.model.DisasterReport;
import java.util.List;

public interface DisasterReportService {
    DisasterReport createReport(DisasterReport report, Long userId);
    List<DisasterReport> getAllReports();
    List<DisasterReport> getReportsByLocation(String location);
    List<DisasterReport> getReportsBySeverity(String severity);
}


