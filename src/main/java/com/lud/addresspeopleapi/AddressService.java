package com.lud.addresspeopleapi;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    public void createAddressWithUser(Address address, Long userId) {
        Person user = personRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        address.setPerson(user);
        addressRepository.save(address);
    }


    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public boolean deleteAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isPresent()) {
            addressRepository.delete(optionalAddress.get());
            return true; // Endereço foi excluído com sucesso
        }

        return false; // Endereço não foi encontrado
    }
    public List<Address> findByStreet(String street) {
        return addressRepository.findByStreet(street);
    }

    public List<Address> findByDistrict(String district) {
        return addressRepository.findByDistrict(district);
    }

    public List<Address> findByCity(String city) {
        return addressRepository.findByCityContaining(city);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        Address existingAddress = getAddressById(id);

        if (existingAddress != null) {
            // Atualize as informações do endereço com os dados do updatedAddress
            existingAddress.setStreet(updatedAddress.getStreet());
            existingAddress.setDistrict(updatedAddress.getDistrict());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setHouseNumber(updatedAddress.getHouseNumber());
            existingAddress.setState(updatedAddress.getState());
            existingAddress.setCountry(updatedAddress.getCountry());

            return addressRepository.save(existingAddress);
        }

        return null; // Endereço não encontrado
    }


}
