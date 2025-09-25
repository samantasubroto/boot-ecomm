package com.example.springboot_demo.model.entity;

import com.example.springboot_demo.model.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address extends ItemType {

    @Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid street no")
    private String streetNo;

    @Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid building name")
    private String buildingName;

    @NotNull
    @Pattern(regexp = "[A-Za-z0-9\\s-]{3,}", message = "Not a valid locality name")
    private String locality;

    @NotNull(message = "City name cannot be null")
    @Pattern(regexp = "[A-Za-z\\s]{2,}", message = "Not a valid city name")
    private String city;

    @NotNull(message = "State name cannot be null")
    private String state;

    @NotNull(message = "Pincode cannot be null")
    @Pattern(regexp = "[0-9]{6}", message = "Pincode not valid. Must be 6 digits")
    private String pincode;

    @Column(name = "is_primary_address")
    private boolean isPrimaryAddress;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Address(String streetNo, String buildingName, String locality, String city, String state, String pincode, boolean isPrimaryAddress) {
        this.streetNo = streetNo;
        this.buildingName = buildingName;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.isPrimaryAddress = isPrimaryAddress;
    }
}
