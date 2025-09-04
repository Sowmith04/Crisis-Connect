package com.disastermanagement.backend.service;

import com.disastermanagement.backend.model.Volunteer;
import java.util.List;

public interface VolunteerService {
    Volunteer addVolunteer(Volunteer volunteer);
    List<Volunteer> getAllVolunteers();
    Volunteer getVolunteerById(Long id);
    Volunteer updateVolunteer(Long id, Volunteer volunteer);
    void deleteVolunteer(Long id);
}

