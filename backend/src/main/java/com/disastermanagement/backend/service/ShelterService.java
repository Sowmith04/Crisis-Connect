package com.disastermanagement.backend.service;

import com.disastermanagement.backend.model.Shelter;
import java.util.List;

public interface ShelterService {
    Shelter addShelter(Shelter shelter);
    List<Shelter> getAllShelters();
    Shelter getShelterById(Long id);
    Shelter updateShelter(Long id, Shelter shelter);
    void deleteShelter(Long id);
}


