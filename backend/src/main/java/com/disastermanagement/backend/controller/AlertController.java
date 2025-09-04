package com.disastermanagement.backend.controller;

import com.disastermanagement.backend.model.Alert;
import com.disastermanagement.backend.model.DisasterReport;
import com.disastermanagement.backend.repository.DisasterReportRepository;
import com.disastermanagement.backend.service.AlertService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "http://localhost:3000")
public class AlertController {

    private final AlertService alertService;
    private final DisasterReportRepository disasterReportRepository;

    public AlertController(AlertService alertService, DisasterReportRepository disasterReportRepository) {
        this.alertService = alertService;
        this.disasterReportRepository = disasterReportRepository;
    }

    // Create alert linked to a disaster report
    @PostMapping
    public Alert createAlert(@RequestParam Long disasterReportId, @Valid @RequestBody Alert alert) {
        DisasterReport disasterReport = disasterReportRepository.findById(disasterReportId)
                .orElseThrow(() -> new IllegalArgumentException("Disaster Report not found"));
        alert.setDisasterReport(disasterReport);
        return alertService.createAlert(alert);
    }

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @GetMapping("/{id}")
    public Alert getAlertById(@PathVariable Long id) {
        return alertService.getAlertById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return "Alert deleted successfully!";
    }
}
