package com.disastermanagement.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "relief_resources")
public class ReliefResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 2, max = 100)
    private String resourceName;

    @NotBlank @Size(min = 2, max = 50)
    private String resourceType;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    @NotBlank @Size(min = 2, max = 150)
    private String location;

    @NotBlank @Size(min = 2, max = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    public ReliefResource() {}

    public ReliefResource(String resourceName, String resourceType, int quantity, String location, String status) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.quantity = quantity;
        this.location = location;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getResourceName() { return resourceName; }
    public void setResourceName(String resourceName) { this.resourceName = resourceName; }
    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Shelter getShelter() { return shelter; }
    public void setShelter(Shelter shelter) { this.shelter = shelter; }
}
