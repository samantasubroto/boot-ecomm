package com.example.springboot_demo.service.address;

import com.example.springboot_demo.model.entity.Address;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface AddressService {

    Address getAddressById(final Long addressId);

    List<Address> getUserAddresses();

    List<Address> addUserAddress(final Address address);

    Address deleteAddress(final Long id);

    Address updateAddress(final Long id, final Address address);
}
