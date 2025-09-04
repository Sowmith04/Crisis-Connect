package com.disastermanagement.backend.controller;

import com.disastermanagement.backend.model.Volunteer;
import com.disastermanagement.backend.service.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin(origins = "http://localhost:3000")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public Volunteer addVolunteer(@Valid @RequestBody Volunteer volunteer) {
        return volunteerService.addVolunteer(volunteer);
    }

    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }

    @GetMapping("/{id}")
    public Volunteer getVolunteerById(@PathVariable Long id) {
        return volunteerService.getVolunteerById(id);
    }

    @PutMapping("/{id}")
    public Volunteer updateVolunteer(@PathVariable Long id, @Valid @RequestBody Volunteer volunteer) {
        return volunteerService.updateVolunteer(id, volunteer);
    }

    @DeleteMapping("/{id}")
    public String deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        return "Volunteer deleted successfully!";
    }
}
