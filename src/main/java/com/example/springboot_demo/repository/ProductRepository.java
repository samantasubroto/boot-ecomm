package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductById(Long id);

    @Query("SELECT p FROM Product p WHERE p.code = :code")
    Product getProductByCode(String code);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> getProductsByName(String name);
}
