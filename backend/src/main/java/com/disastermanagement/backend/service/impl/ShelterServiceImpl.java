package com.disastermanagement.backend.service.impl;

import com.disastermanagement.backend.model.Shelter;
import com.disastermanagement.backend.repository.ShelterRepository;
import com.disastermanagement.backend.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterServiceImpl implements ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    @Override
    public Shelter addShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    @Override
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    @Override
    public Shelter getShelterById(Long id) {
        return shelterRepository.findById(id).orElse(null);
    }

    @Override
    public Shelter updateShelter(Long id, Shelter shelter) {
        Shelter existing = shelterRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(shelter.getName());
            existing.setLocation(shelter.getLocation());
            existing.setCapacity(shelter.getCapacity());
            existing.setOccupied(shelter.getOccupied());
            existing.setContact(shelter.getContact());
            return shelterRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteShelter(Long id) {
        shelterRepository.deleteById(id);
    }
}
