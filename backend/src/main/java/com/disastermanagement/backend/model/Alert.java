package com.disastermanagement.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 5, max = 500)
    private String message;

    @NotBlank
    @Pattern(regexp = "HIGH|MEDIUM|LOW", message = "Severity must be HIGH, MEDIUM or LOW")
    private String severity;

    @NotBlank @Size(min = 2, max = 150)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disaster_report_id", nullable = false)
    private DisasterReport disasterReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
}
