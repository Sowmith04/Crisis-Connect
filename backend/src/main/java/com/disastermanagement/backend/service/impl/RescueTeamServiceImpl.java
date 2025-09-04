package com.disastermanagement.backend.service.impl;

import com.disastermanagement.backend.model.RescueTeam;
import com.disastermanagement.backend.repository.RescueTeamRepository;
import com.disastermanagement.backend.service.RescueTeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RescueTeamServiceImpl implements RescueTeamService {

    private final RescueTeamRepository rescueTeamRepository;

    public RescueTeamServiceImpl(RescueTeamRepository rescueTeamRepository) {
        this.rescueTeamRepository = rescueTeamRepository;
    }

    @Override
    public RescueTeam registerTeam(RescueTeam team) {
        return rescueTeamRepository.save(team);
    }

    @Override
    public List<RescueTeam> getAllTeams() {
        return rescueTeamRepository.findAll();
    }

    @Override
    public RescueTeam getTeamById(Long id) {
        return rescueTeamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rescue team not found"));
    }

    @Override
    public void deleteTeam(Long id) {
        rescueTeamRepository.deleteById(id);
    }
}

