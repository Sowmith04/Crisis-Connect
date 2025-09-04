package com.disastermanagement.backend.service.impl;

import com.disastermanagement.backend.model.ReliefResource;
import com.disastermanagement.backend.repository.ReliefResourceRepository;
import com.disastermanagement.backend.service.ReliefResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReliefResourceServiceImpl implements ReliefResourceService {

    @Autowired
    private ReliefResourceRepository repository;

    @Override
    public ReliefResource addResource(ReliefResource resource) {
        return repository.save(resource);
    }

    @Override
    public List<ReliefResource> getAllResources() {
        return repository.findAll();
    }

    @Override
    public ReliefResource getResourceById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ReliefResource updateResource(Long id, ReliefResource resource) {
        ReliefResource existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setResourceName(resource.getResourceName());
            existing.setResourceType(resource.getResourceType());
            existing.setQuantity(resource.getQuantity());
            existing.setLocation(resource.getLocation());
            existing.setStatus(resource.getStatus());
            return repository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteResource(Long id) {
        repository.deleteById(id);
    }
}

