package com.disastermanagement.backend.service;

import com.disastermanagement.backend.model.ReliefResource;

import java.util.List;

public interface ReliefResourceService {
    ReliefResource addResource(ReliefResource resource);
    List<ReliefResource> getAllResources();
    ReliefResource getResourceById(Long id);
    ReliefResource updateResource(Long id, ReliefResource resource);
    void deleteResource(Long id);
}

