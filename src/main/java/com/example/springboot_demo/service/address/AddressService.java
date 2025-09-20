package com.example.springboot_demo.service.address;

import com.example.springboot_demo.model.entity.Address;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface AddressService {

    Address getAddressById(final Long addressId, final String userId);

    List<Address> getUserAddresses(final String userId);

    List<Address> addUserAddress(final Address address, final String userId);

    Address deleteAddress(final Long id, final String userId);

    Address updateAddress(final Long id, final String userId, final Address address);
}
