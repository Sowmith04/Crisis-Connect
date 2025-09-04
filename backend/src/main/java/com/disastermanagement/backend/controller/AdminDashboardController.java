package com.disastermanagement.backend.controller;

import com.disastermanagement.backend.model.Alert;
import com.disastermanagement.backend.model.DisasterReport;
import com.disastermanagement.backend.model.Volunteer;
import com.disastermanagement.backend.service.AdminDashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    public AdminDashboardController(AdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("/overview")
    public Map<String, Object> getOverview() {
        return adminDashboardService.getSystemOverview();
    }

    @GetMapping("/reports/pending")
    public List<DisasterReport> getPendingReports() {
        return adminDashboardService.getPendingReports();
    }

    @PutMapping("/volunteers/{id}/approve")
    public Volunteer approveVolunteer(@PathVariable Long id) {
        return adminDashboardService.approveVolunteer(id);
    }

    @PutMapping("/volunteers/{id}/reject")
    public Volunteer rejectVolunteer(@PathVariable Long id) {
        return adminDashboardService.rejectVolunteer(id);
    }

    @PostMapping("/rescue/assign")
    public String assignRescueTeam(@RequestParam Long rescueTeamId, @RequestParam Long disasterReportId) {
        return adminDashboardService.assignRescueTeam(rescueTeamId, disasterReportId);
    }

    @PostMapping("/resources/allocate")
    public String allocateResources(@RequestParam Long shelterId,
                                    @RequestParam String resourceType,
                                    @RequestParam int quantity) {
        return adminDashboardService.allocateResources(shelterId, resourceType, quantity);
    }

    @PostMapping("/alerts")
    public Alert createAlert(@RequestParam String message,
                             @RequestParam String region,
                             @RequestParam String severity) {
        return adminDashboardService.createAlert(message, region, severity);
    }
}
