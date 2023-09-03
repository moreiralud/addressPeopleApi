package com.lud.addresspeopleapi;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressRepository addressRepository;
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressRepository addressRepository, AddressService addressService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        boolean addressDeleted = addressService.deleteAddress(id);
        if (addressDeleted) {
            return ResponseEntity.ok("Endereço excluído com sucesso");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createAddress(@RequestBody @Valid Address address,
                                                @RequestParam Long userId) {
        try {
            addressService.createAddressWithUser(address, userId);
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok("Endereço criado com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }
    @GetMapping("/search/by-street")
    public ResponseEntity<List<Address>> searchByStreet(@RequestParam String street) {
        List<Address> addresses = addressService.findByStreet(street);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/search/by-district")
    public ResponseEntity<List<Address>> searchByDistrict(@RequestParam String district) {
        List<Address> addresses = addressService.findByDistrict(district);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/search/by-city")
    public ResponseEntity<List<Address>> searchByCity(@RequestParam String city) {
        List<Address> addresses = addressService.findByCity(city.trim());
        return ResponseEntity.ok(addresses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
        Address updated = addressService.updateAddress(id, updatedAddress);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

