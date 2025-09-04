package com.disastermanagement.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "rescue_team")
public class RescueTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 2, max = 100)
    private String teamName;

    @NotBlank
    @Pattern(regexp = "^[0-9+\\-() ]{7,20}$", message = "Invalid contact number")
    private String contactNumber;

    @Size(max = 100)
    private String specialization;

    @NotBlank @Size(min = 2, max = 150)
    private String location;

    @ManyToOne
    @JoinColumn(name = "disaster_report_id")
    private DisasterReport assignedDisaster;

    public RescueTeam() {}

    public RescueTeam(String teamName, String contactNumber, String specialization, String location) {
        this.teamName = teamName;
        this.contactNumber = contactNumber;
        this.specialization = specialization;
        this.location = location;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public DisasterReport getAssignedDisaster() { return assignedDisaster; }
    public void setAssignedDisaster(DisasterReport assignedDisaster) { this.assignedDisaster = assignedDisaster; }
}
