package com.disastermanagement.backend.controller;

import com.disastermanagement.backend.model.DisasterReport;
import com.disastermanagement.backend.service.DisasterReportService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:3000")
public class DisasterReportController {

    private final DisasterReportService reportService;

    public DisasterReportController(DisasterReportService reportService) {
        this.reportService = reportService;
    }

    // Create a new disaster report
    @PostMapping("/create/{userId}")
    public DisasterReport createReport(@Valid @RequestBody DisasterReport report, @PathVariable Long userId) {
        return reportService.createReport(report, userId);
    }

    @GetMapping
    public List<DisasterReport> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/location/{location}")
    public List<DisasterReport> getByLocation(@PathVariable String location) {
        return reportService.getReportsByLocation(location);
    }

    @GetMapping("/severity/{severity}")
    public List<DisasterReport> getBySeverity(@PathVariable String severity) {
        return reportService.getReportsBySeverity(severity);
    }

    @GetMapping("/get")
    public String movie(){
        return "movie getting";
    }
}
