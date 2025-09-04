package com.disastermanagement.backend.service;

import com.disastermanagement.backend.model.RescueTeam;
import java.util.List;

public interface RescueTeamService {
    RescueTeam registerTeam(RescueTeam team);
    List<RescueTeam> getAllTeams();
    RescueTeam getTeamById(Long id);
    void deleteTeam(Long id);
}

