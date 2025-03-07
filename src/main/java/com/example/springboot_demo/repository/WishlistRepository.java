package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<WishList, Long> {

    @Query("SELECT w FROM WishList w LEFT JOIN FETCH w.products WHERE w.customer.id = :customerId")
    Optional<WishList> findWishlistByCustomer(@Param("customerId") Long customerId);

}
