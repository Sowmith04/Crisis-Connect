package com.disastermanagement.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 2, max = 100)
    private String name;

    @NotBlank @Size(min = 2, max = 150)
    private String location;

    @Min(0)
    private int capacity;

    @Min(0)
    private int occupied;

    @Pattern(regexp = "^$|^[0-9+\\-() ]{7,20}$", message = "Invalid contact number")
    private String contact;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Volunteer> volunteers = new ArrayList<>();

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReliefResource> reliefResources = new ArrayList<>();

    public Shelter() {}

    public Shelter(String name, String location, int capacity, int occupied, String contact) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.occupied = occupied;
        this.contact = contact;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public int getOccupied() { return occupied; }
    public void setOccupied(int occupied) { this.occupied = occupied; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
