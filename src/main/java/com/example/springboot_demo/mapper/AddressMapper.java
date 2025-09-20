package com.example.springboot_demo.mapper;

import com.example.springboot_demo.model.entity.Address;
import com.example.springboot_demo.model.wsDto.AddressDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AddressMapper {

    public static AddressDTO toDTO(Address address) {
        if (address == null) return null;
        return new AddressDTO(
                address.getId(),
                address.getStreetNo(),
                address.getBuildingName(),
                address.getLocality(),
                address.getCity(),
                address.getState(),
                address.getPincode(),
                address.isPrimaryAddress()
        );
    }

    // DTO → Entity
    public static Address toEntity(AddressDTO dto) {
        if (dto == null) return null;
        Address address = new Address();
        address.setStreetNo(dto.getStreetNo());
        address.setBuildingName(dto.getBuildingName());
        address.setLocality(dto.getLocality());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPincode(dto.getPincode());
        address.setPrimaryAddress(dto.isPrimaryAddress());
        return address;
    }

    // List<Entity> → List<DTO>
    public static List<AddressDTO> toDTOList(List<Address> addresses) {
        return addresses.stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

    // List<DTO> → List<Entity>
    public static List<Address> toEntityList(List<AddressDTO> dtos) {
        return dtos.stream()
                .map(AddressMapper::toEntity)
                .collect(Collectors.toList());
    }
}