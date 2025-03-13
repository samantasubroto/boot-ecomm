package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
