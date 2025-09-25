package com.example.springboot_demo.service.user;

import com.example.springboot_demo.model.entity.User;

public interface UserService {

    User getUserByEmail(final String userId);

    void saveUser(final User user);

    User getCurrentUser();

    User save(User user);
}
