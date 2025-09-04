package com.disastermanagement.backend.service;

import com.disastermanagement.backend.model.*;
import com.disastermanagement.backend.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminDashboardService {

    private final UserRepository userRepository;
    private final DisasterReportRepository disasterReportRepository;
    private final RescueTeamRepository rescueTeamRepository;
    private final ShelterRepository shelterRepository;
    private final VolunteerRepository volunteerRepository;
    private final ReliefResourceRepository reliefResourceRepository;
    private final AlertRepository alertRepository;

    public AdminDashboardService(UserRepository userRepository,
                                 DisasterReportRepository disasterReportRepository,
                                 RescueTeamRepository rescueTeamRepository,
                                 ShelterRepository shelterRepository,
                                 VolunteerRepository volunteerRepository,
                                 ReliefResourceRepository reliefResourceRepository,
                                 AlertRepository alertRepository) {
        this.userRepository = userRepository;
        this.disasterReportRepository = disasterReportRepository;
        this.rescueTeamRepository = rescueTeamRepository;
        this.shelterRepository = shelterRepository;
        this.volunteerRepository = volunteerRepository;
        this.reliefResourceRepository = reliefResourceRepository;
        this.alertRepository = alertRepository;
    }

    public Map<String, Object> getSystemOverview() {
        Map<String, Object> overview = new HashMap<>();
        overview.put("totalUsers", userRepository.count());
        overview.put("totalDisasterReports", disasterReportRepository.count());
        overview.put("activeRescueTeams", rescueTeamRepository.count()); // can filter active if needed
        overview.put("availableVolunteers", volunteerRepository.count()); // can filter by status
        overview.put("sheltersAvailable", shelterRepository.count());

        // Example: Resource stock summary
        List<ReliefResource> resources = reliefResourceRepository.findAll();
        Map<String, Integer> stock = new HashMap<>();
        for (ReliefResource r : resources) {
            stock.put(r.getResourceType(),
                    stock.getOrDefault(r.getResourceType(), 0) + r.getQuantity());
        }
        overview.put("resourcesStock", stock);

        return overview;
    }

    public List<DisasterReport> getPendingReports() {
        return disasterReportRepository.findByType("PENDING");
    }

    public Volunteer approveVolunteer(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id).orElseThrow();
        volunteer.setStatus("APPROVED");
        return volunteerRepository.save(volunteer);
    }

    public Volunteer rejectVolunteer(Long id) {
        Volunteer volunteer = volunteerRepository.findById(id).orElseThrow();
        volunteer.setStatus("REJECTED");
        return volunteerRepository.save(volunteer);
    }

    public String assignRescueTeam(Long rescueTeamId, Long disasterReportId) {
        RescueTeam team = rescueTeamRepository.findById(rescueTeamId).orElseThrow();
        DisasterReport report = disasterReportRepository.findById(disasterReportId).orElseThrow();

        // Example: assigning team to disaster
        team.setAssignedDisaster(report);
        rescueTeamRepository.save(team);

        return "Rescue team " + team.getTeamName() + " assigned to disaster " + report.getId();
    }

    public String allocateResources(Long shelterId, String resourceType, int quantity) {
        Shelter shelter = shelterRepository.findById(shelterId).orElseThrow();
        ReliefResource resource = new ReliefResource();
        resource.setResourceType(resourceType);
        resource.setQuantity(quantity);
        resource.setShelter(shelter);
        reliefResourceRepository.save(resource);

        return "Allocated " + quantity + " " + resourceType + " to shelter " + shelter.getName();
    }

    public Alert createAlert(String message, String region, String severity) {
        Alert alert = new Alert();
        alert.setMessage(message);
        alert.setLocation(region);
        alert.setSeverity(severity);
        return alertRepository.save(alert);
    }
}
