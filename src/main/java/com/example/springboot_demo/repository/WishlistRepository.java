package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<WishList, Long> {

    @Query("SELECT w FROM WishList w LEFT JOIN FETCH w.products WHERE w.user.id = :customerId")
    Optional<WishList> findWishlistByCustomer(@Param("customerId") Long customerId);

}
