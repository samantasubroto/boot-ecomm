package com.example.springboot_demo.controllers;

import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.wsDto.LoginDTO;
import com.example.springboot_demo.service.authentication.UserAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserAuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(this.authService.register(customer), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDTO credential) {
        return new ResponseEntity<>(this.authService.login(credential.getEmail(), credential.getPassword()), HttpStatus.FOUND);
    }
}
