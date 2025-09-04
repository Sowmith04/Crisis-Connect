package com.disastermanagement.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "volunteers")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 2, max = 100)
    private String name;

    @Pattern(regexp = "^$|^[0-9+\\-() ]{7,20}$", message = "Invalid contact number")
    private String contactNumber;

    @Email(message = "Invalid email")
    private String email;

    @Size(max = 250)
    private String skills;

    private boolean availability = true;

    @Size(max = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public boolean getAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Shelter getShelter() { return shelter; }
    public void setShelter(Shelter shelter) { this.shelter = shelter; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
