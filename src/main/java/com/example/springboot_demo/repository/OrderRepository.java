package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.entity.Order;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.email = :customerId")
    List<Order> fetchAllOrders(@Param("customerId") String customerId);
}
