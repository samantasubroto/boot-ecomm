package com.example.springboot_demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer(String firstName, String lastName, String email, String password, String phone, Set<String> roles, List<Address> address) {
        super(firstName, lastName, email, password, phone, roles);
        this.address = address;
    }
}
