package com.example.springboot_demo.service.address.impl;

import com.example.springboot_demo.model.entity.Address;
import com.example.springboot_demo.model.entity.User;
import com.example.springboot_demo.repository.AddressRepository;
import com.example.springboot_demo.service.address.AddressService;
import com.example.springboot_demo.service.user.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserService userService;

    public Address getAddressById(@NotNull final Long addressId) {
        User user = userService.getCurrentUser();

        return addressRepository.findById(addressId)
                .filter(address -> address.getUser().equals(user))
                .orElseThrow(() -> new RuntimeException("Address Not Found for user: " + user.getUuid()));
    }

    @Override
    public List<Address> getUserAddresses() {
        User user = userService.getCurrentUser();

        if (user != null) {
            return addressRepository.getUsersAddresses(user);
        }
        return List.of();
    }

    @Override
    public List<Address> addUserAddress(@NotNull final Address address) {
        User user = userService.getCurrentUser();

        if (user == null) {
            throw new RuntimeException("User Not Found");
        }
        List<Address> addresses = user.getAddress();
        if (address.isPrimaryAddress()) {
            addresses.stream().forEach(e -> e.setPrimaryAddress(false));
        }
        address.setUser(user);
        addresses.add(address);
        user.setAddress(addresses);
        addressRepository.save(address);
        userService.saveUser(user);
        return addresses;
    }

    @Override
    public Address deleteAddress(final Long id) {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new RuntimeException("User Not Found");
        }
        Address address = user.getAddress().stream()
                .filter(a -> id.equals(a.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Address not found"));
        user.getAddress().remove(address);
        userService.saveUser(user);
        return address;
    }

    @Override
    public Address updateAddress(final Long id, final Address updatedAddress) {
        Address existingAddress = getAddressById(id);

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
