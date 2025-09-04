package com.disastermanagement.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class DisasterReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 80)
    private String type;        // e.g., Flood, Earthquake, Fire

    @NotBlank @Size(min = 2, max = 150)
    private String location;

    @NotBlank
    @Pattern(regexp = "HIGH|MEDIUM|LOW", message = "Severity must be HIGH, MEDIUM or LOW")
    private String severity;

    @NotBlank @Size(min = 5, max = 1000)
    private String description;

    private LocalDateTime reportedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reportedBy;

    @OneToMany(mappedBy = "disasterReport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alert> alerts = new ArrayList<>();

    @OneToMany(mappedBy = "assignedDisaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RescueTeam> rescueTeams = new ArrayList<>();
}
