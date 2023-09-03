package com.lud.addresspeopleapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByStreet(String street);

    List<Address> findByDistrict(String district);

//    @Query("SELECT a FROM Address a WHERE a.city LIKE %:city%")
    List<Address> findByCityContaining(String city);
}
