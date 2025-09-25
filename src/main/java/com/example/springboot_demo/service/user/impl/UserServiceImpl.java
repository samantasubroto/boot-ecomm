package com.example.springboot_demo.service.user.impl;

import com.example.springboot_demo.config.SessionUser;
import com.example.springboot_demo.model.entity.User;
import com.example.springboot_demo.repository.UserRepository;
import com.example.springboot_demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(final String userId) {
        return this.userRepository.getUserByEmail(userId);
    }

    @Override
    public void saveUser(final User user) {
        this.userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SessionUser sessionUser) {
            if (sessionUser.getEmail() != null) {
                return getUserByEmail(sessionUser.getEmail());
            }
        }
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
