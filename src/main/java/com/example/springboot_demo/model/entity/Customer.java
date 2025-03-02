package com.example.springboot_demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
}
