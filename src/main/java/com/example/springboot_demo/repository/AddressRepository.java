package com.example.springboot_demo.repository;

import com.example.springboot_demo.model.entity.Address;
import com.example.springboot_demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.user = :user")
    List<Address> getUsersAddresses(@Param("user") User user);
}
