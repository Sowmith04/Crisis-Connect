package com.disastermanagement.backend.service;

import com.disastermanagement.backend.model.Alert;

import java.util.List;

public interface AlertService {
    Alert createAlert(Alert alert);
    List<Alert> getAllAlerts();
    Alert getAlertById(Long id);
    void deleteAlert(Long id);
}


