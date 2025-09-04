package com.disastermanagement.backend.controller;

import com.disastermanagement.backend.model.Shelter;
import com.disastermanagement.backend.service.ShelterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shelters")
@CrossOrigin(origins = "http://localhost:3000")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @PostMapping
    public Shelter addShelter(@Valid @RequestBody Shelter shelter) {
        return shelterService.addShelter(shelter);
    }

    @GetMapping
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @GetMapping("/{id}")
    public Shelter getShelterById(@PathVariable Long id) {
        return shelterService.getShelterById(id);
    }

    @PutMapping("/{id}")
    public Shelter updateShelter(@PathVariable Long id, @Valid @RequestBody Shelter shelter) {
        return shelterService.updateShelter(id, shelter);
    }

    @DeleteMapping("/{id}")
    public String deleteShelter(@PathVariable Long id) {
        shelterService.deleteShelter(id);
        return "Shelter with ID " + id + " deleted successfully.";
    }
}
