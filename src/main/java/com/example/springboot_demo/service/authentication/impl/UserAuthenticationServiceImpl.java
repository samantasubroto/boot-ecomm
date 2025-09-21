package com.example.springboot_demo.service.authentication.impl;

import com.example.springboot_demo.model.entity.User;
import com.example.springboot_demo.repository.CartRepository;
import com.example.springboot_demo.repository.UserRepository;
import com.example.springboot_demo.service.authentication.UserAuthenticationService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Value("${firebase.api-key}")
    private String firebaseApiKey;

    public User register(@NonNull final User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Email and password must not be null");
        }

        if (userRepository.getCustomerByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }

        UserRecord firebaseUser = null;
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .setEmailVerified(false)
                    .setDisabled(false);

            firebaseUser = FirebaseAuth.getInstance().createUser(request);
            user.setUuid(firebaseUser.getUid());
            user.setActive(true);
            user.setLastLogin(LocalDateTime.now());
            return userRepository.save(user);
        } catch (ConstraintViolationException e) {
            if (firebaseUser != null) {
                try {
                    FirebaseAuth.getInstance().deleteUser(firebaseUser.getUid());
                } catch (Exception ex) {
                    throw new RuntimeException("DB save failed & failed to rollback Firebase user: " + ex.getMessage(), ex);
                }
            }
            throw new RuntimeException("DB save failed due to constraint violation: " + e.getMessage(), e);
        } catch (Exception e) {
            if (firebaseUser != null) {
                try {
                    FirebaseAuth.getInstance().deleteUser(firebaseUser.getUid());
                } catch (Exception ex) {
                    throw new RuntimeException("DB save failed & failed to rollback Firebase user: " + ex.getMessage(), ex);
                }
            }
            throw new RuntimeException("Error creating user with provided information: " + e.getMessage(), e);
        }
    }


    public Map<String, Object> login(final String email, final String password) {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + firebaseApiKey;

        Map<String, Object> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);
        payload.put("returnSecureToken", true);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, payload, Map.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error Finding login details please try again " + e);
        }
    }

    @Override
    public void logout(final String uid) {
        try {
            FirebaseAuth.getInstance().revokeRefreshTokens(uid);
        } catch (Exception e) {
            throw new RuntimeException("Error during logout for user: " + uid, e);
        }
    }
}
