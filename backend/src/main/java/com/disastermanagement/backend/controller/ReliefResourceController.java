package com.disastermanagement.backend.controller;

import com.disastermanagement.backend.model.ReliefResource;
import com.disastermanagement.backend.service.ReliefResourceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "http://localhost:3000")
public class ReliefResourceController {

    @Autowired
    private ReliefResourceService service;

    @PostMapping
    public ReliefResource addResource(@Valid @RequestBody ReliefResource resource) {
        return service.addResource(resource);
    }

    @GetMapping
    public List<ReliefResource> getAllResources() {
        return service.getAllResources();
    }

    @GetMapping("/{id}")
    public ReliefResource getResourceById(@PathVariable Long id) {
        return service.getResourceById(id);
    }

    @PutMapping("/{id}")
    public ReliefResource updateResource(@PathVariable Long id, @Valid @RequestBody ReliefResource resource) {
        return service.updateResource(id, resource);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        service.deleteResource(id);
    }
}
