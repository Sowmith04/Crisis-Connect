package com.disastermanagement.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50)
    private String username;
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100)
    private String password;
    @NotBlank(message = "Role is required")
    private String role;  // USER / ADMIN / VOLUNTEER

    // 🔗 One user can file multiple disaster reports
    @OneToMany(mappedBy = "reportedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DisasterReport> disasterReports;

    // 🔗 One user can have one volunteer profile
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Volunteer volunteerProfile;

    // 🔗 One admin user can create many alerts
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alert> alerts;
}





