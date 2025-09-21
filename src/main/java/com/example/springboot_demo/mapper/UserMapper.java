package com.example.springboot_demo.mapper;

import com.example.springboot_demo.model.entity.User;
import com.example.springboot_demo.model.wsDto.UserDTO;
import com.google.firebase.auth.UserRecord;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class UserMapper {

    // 🔹 Entity → DTO
    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        return new UserDTO(
                user.getUuid(),
                user.getCustomerId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getRoles(),
                user.isActive(),
                user.getLastLogin()
        );
    }

    // 🔹 DTO → Entity
    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUuid(dto.getUuid());
        user.setCustomerId(dto.getCustomerId());
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setRoles(dto.getRoles());
        user.setActive(dto.isActive());
        user.setLastLogin(dto.getLastLogin());

        return user;
    }

    // 🔹 Firebase UserRecord → Entity
    public static User fromFirebase(UserRecord record) {
        if (record == null) return null;

        User user = new User();
        user.setUuid(record.getUid());
        user.setEmail(record.getEmail());
        user.setPhone(record.getPhoneNumber());
        user.setFirstName(record.getDisplayName()); // ⚠️ might contain full name
        user.setActive(!record.isDisabled());

        if (record.getUserMetadata() != null && record.getUserMetadata().getLastSignInTimestamp() > 0) {
            user.setLastLogin(LocalDateTime.ofEpochSecond(
                    record.getUserMetadata().getLastSignInTimestamp() / 1000,
                    0,
                    ZoneOffset.UTC
            ));
        }

        return user;
    }
}
