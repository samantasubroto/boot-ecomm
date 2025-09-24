package com.example.springboot_demo.service.address.impl;

import com.example.springboot_demo.model.entity.Address;
import com.example.springboot_demo.model.entity.Customer;
import com.example.springboot_demo.repository.AddressRepository;
import com.example.springboot_demo.service.address.AddressService;
import com.example.springboot_demo.service.user.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserService userService;

    public Address getAddressById(@NotNull final Long addressId, @NotNull final String userId) {
        Customer customer = userService.getCustomerByEmail(userId);

        return addressRepository.findById(addressId)
                .filter(address -> address.getCustomer().equals(customer))
                .orElseThrow(() -> new RuntimeException("Address Not Found for user: " + userId));
    }

    @Override
    public List<Address> getUserAddresses(@NonNull final String userId) {
        Customer customer = (Customer) userService.getCurrentCustomer();
        if (customer != null) {
            List<Address> addresses = addressRepository.getUsersAddresses(customer);
            return addresses;
        }
        return List.of();
    }

    @Override
    public List<Address> addUserAddress(@NotNull  final Address address, @NotNull final String userId) {
        Customer customer = (Customer) userService.getCurrentCustomer();
        if (customer == null) {
            throw new RuntimeException("User Not Found");
        }
        List<Address> addresses = customer.getAddress();
        if (address.isPrimaryAddress()) {
            addresses.stream().forEach(e -> e.setPrimaryAddress(false));
        }
        address.setCustomer(customer);
        addresses.add(address);
        customer.setAddress(addresses);
        addressRepository.save(address);
        userService.saveCustomer(customer);
        return addresses;
    }

    @Override
    public Address deleteAddress(final Long id, final String userId) {
        Customer customer = userService.getCustomerByEmail(userId);
        if (customer == null) {
            throw new RuntimeException("User Not Found");
        }
        Address address = customer.getAddress().stream()
                .filter(a -> id.equals(a.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found"));
        customer.getAddress().remove(address);
        userService.saveCustomer(customer);
        return address;
    }

    @Override
    public Address updateAddress(final Long id, final String userId, final Address updatedAddress) {
        Address existingAddress = getAddressById(id, userId);

        existingAddress.setStreetNo(updatedAddress.getStreetNo());
        existingAddress.setBuildingName(updatedAddress.getBuildingName());
        existingAddress.setLocality(updatedAddress.getLocality());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setState(updatedAddress.getState());
        existingAddress.setPincode(updatedAddress.getPincode());
        existingAddress.setPrimaryAddress(updatedAddress.isPrimaryAddress());

        return addressRepository.save(existingAddress);
    }
}
