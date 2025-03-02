package com.example.springboot_demo.entityListeners;

import com.example.springboot_demo.exceptions.InvalidUserEmailException;
import com.example.springboot_demo.model.entity.User;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class UserModelListner {

    @PrePersist
    @PreUpdate
    public void preSave(User user) throws Exception {
        if (!user.getEmail().isEmpty() && !user.getEmail().isBlank()) {
            user.setCustomerId(user.getEmail());
        } else {
            throw new InvalidUserEmailException("Email Cannot be left Empty ");
        }
        user.setUsername(user.getFirstName() + " " + user.getLastName());
    }
}
