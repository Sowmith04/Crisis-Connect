package com.disastermanagement.backend.controller;

import com.disastermanagement.backend.model.RescueTeam;
import com.disastermanagement.backend.service.RescueTeamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rescue-teams")
@CrossOrigin(origins = "http://localhost:3000")
public class RescueTeamController {

    private final RescueTeamService rescueTeamService;

    public RescueTeamController(RescueTeamService rescueTeamService) {
        this.rescueTeamService = rescueTeamService;
    }

    // Register new rescue team
    @PostMapping("/register")
    public RescueTeam registerTeam(@Valid @RequestBody RescueTeam team) {
        return rescueTeamService.registerTeam(team);
    }

    // View all teams
    @GetMapping
    public List<RescueTeam> getAllTeams() {
        return rescueTeamService.getAllTeams();
    }

    // View single team
    @GetMapping("/{id}")
    public RescueTeam getTeamById(@PathVariable Long id) {
        return rescueTeamService.getTeamById(id);
    }

    // Delete team
    @DeleteMapping("/{id}")
    public String deleteTeam(@PathVariable Long id) {
        rescueTeamService.deleteTeam(id);
        return "Rescue Team deleted successfully";
    }
}
