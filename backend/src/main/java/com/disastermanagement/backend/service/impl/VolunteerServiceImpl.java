package com.disastermanagement.backend.service.impl;

import com.disastermanagement.backend.model.Volunteer;
import com.disastermanagement.backend.repository.VolunteerRepository;
import com.disastermanagement.backend.service.VolunteerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Volunteer addVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Override
    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id).orElse(null);
    }

    @Override
    public Volunteer updateVolunteer(Long id, Volunteer volunteer) {
        Volunteer existing = volunteerRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(volunteer.getName());
            existing.setContactNumber(volunteer.getContactNumber());
            existing.setEmail(volunteer.getEmail());
            existing.setSkills(volunteer.getSkills());
            existing.setAvailability(volunteer.getAvailability());
            existing.setShelter(volunteer.getShelter());
            return volunteerRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}
