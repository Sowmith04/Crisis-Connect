package com.disastermanagement.backend.service.impl;

import com.disastermanagement.backend.model.Alert;
import com.disastermanagement.backend.repository.AlertRepository;
import com.disastermanagement.backend.service.AlertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;

    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public Alert createAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    @Override
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public Alert getAlertById(Long id) {
        return alertRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }
}


