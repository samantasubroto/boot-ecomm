package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.model.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getCustomerByEmail(@Param("email") String email);
}
