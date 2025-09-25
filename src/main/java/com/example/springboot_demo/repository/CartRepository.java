package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.entity.Cart;
import com.example.springboot_demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user = :user")
    Cart getUsersCart(@Param("user") User user);
}
