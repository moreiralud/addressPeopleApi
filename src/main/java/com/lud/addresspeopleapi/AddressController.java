package com.lud.addresspeopleapi;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
        updatedAddress.setId(id);
        return addressRepository.save(updatedAddress);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        boolean addressDeleted = addressService.deleteAddress(id);
        if (addressDeleted) {
            return ResponseEntity.ok("Endereço excluído com sucesso");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addresses")
    public ResponseEntity<String> createAddress(@RequestBody @Valid Address address,
                                                @RequestParam Long userId) {
        addressService.createAddressWithUser(address, userId);
        return ResponseEntity.ok("Endereço criado com sucesso");
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }
}

