package com.example.springboot_demo.model.wsDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;

@Data
public class LoginDTO {
    @NotNull(message = "Enter Email")
    @NonNull
    @Email
    private String email;

    @NotNull(message = "Please enter the password")
    @NonNull
    @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include A-Z, a-z, 0-9, or special characters !@#$%^&*_")
    private String password;
}
