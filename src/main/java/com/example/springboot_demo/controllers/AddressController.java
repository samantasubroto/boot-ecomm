package com.example.springboot_demo.controllers;

import com.example.springboot_demo.mapper.AddressMapper;
import com.example.springboot_demo.model.wsDto.AddressDTO;
import com.example.springboot_demo.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity getAddressById(@PathVariable Long id, @RequestParam(required = true) String userId) {
        return ResponseEntity.ok(AddressMapper.toDTO(addressService.getAddressById(id, userId)));
    }

    @GetMapping
    public ResponseEntity getAddresses(@RequestParam(required = true) String userId) {
        return ResponseEntity.ok(AddressMapper.toDTOList(addressService.getUserAddresses(userId)));
    }

    @PostMapping
    public ResponseEntity addAddress(@RequestBody(required = true) AddressDTO address,
                                     @RequestParam(required = true) String userId) {
        return ResponseEntity.ok(AddressMapper.toDTOList(addressService.addUserAddress(AddressMapper.toEntity(address), userId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddressById(@PathVariable Long id, @RequestParam(required = true) String userId) {
        return ResponseEntity.ok(AddressMapper.toDTO(addressService.deleteAddress(id, userId)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateAddress(@PathVariable Long id, @RequestParam(required = true) String userId, @RequestBody(required = true) AddressDTO address) {
        return ResponseEntity.ok(AddressMapper.toDTO(addressService.updateAddress(id, userId, AddressMapper.toEntity(address))));
    }
}
