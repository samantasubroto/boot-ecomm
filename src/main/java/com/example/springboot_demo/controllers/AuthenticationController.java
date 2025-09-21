package com.example.springboot_demo.controllers;

import com.example.springboot_demo.mapper.UserMapper;
import com.example.springboot_demo.model.entity.User;
import com.example.springboot_demo.model.wsDto.UserDTO;
import com.example.springboot_demo.service.authentication.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserAuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        User user = authenticationService.register(UserMapper.toEntity(userDTO));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Map<String, Object> response = this.authenticationService.login(body.get("email"), body.get("password"));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody Map<String, String> body) {
        authenticationService.logout(body.get("uid"));
        return ResponseEntity.ok("User logged out successfully");
    }
}
