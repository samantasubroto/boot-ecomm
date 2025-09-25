package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.entityListeners.UserModelListner;
import com.example.springboot_demo.model.ItemType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EntityListeners(UserModelListner.class)
@Table(name = "user")
public class User extends ItemType {

    @Column(unique = true, updatable = false, nullable = false)
    private String uuid;

    private String customerId;

    private String username;

    @Column(name = "firstname")
    @NotNull(message = "First Name cannot be NULL")
    @NonNull
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in first name")
    private String firstName;

    @Column(name = "lastname")
    @NotNull(message = "Last Name cannot be NULL")
    @NonNull
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in last name")
    private String lastName;

    @NotNull(message = "Enter Email")
    @NonNull
    @Email
    private String email;

    @NotNull(message = "Please enter the password")
    @NonNull
    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include A-Z, a-z, 0-9, or special characters !@#$%^&*_")
    private String password;

    @NotNull(message = "Please enter the mobile Number")
    @NonNull
    @Column(unique = true)
    @Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    @NonNull
    private Set<String> roles;

    @Nullable
    private boolean isActive;

    @Nullable
    private LocalDateTime lastLogin;

    @PrePersist
    private void generateUUID() {
        if (this.uuid == null || this.uuid.isEmpty()) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> address;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
}
