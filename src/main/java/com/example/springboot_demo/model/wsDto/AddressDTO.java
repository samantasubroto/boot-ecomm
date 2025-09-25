package com.example.springboot_demo.model.wsDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {

    private Long Id;

    private String streetNo;

    private String buildingName;

    private String locality;

    private String city;

    private String state;

    private String pincode;

    private boolean isPrimaryAddress;
}
