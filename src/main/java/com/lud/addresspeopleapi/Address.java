package com.lud.addresspeopleapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import java.lang.String;

@Entity
public class Address {
    @Id
    @Column
    @NotBlank
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String street;
    @Column
    @NotBlank
    private String district;
    @Column
    @NotBlank
    private String city;
    @Column
    @NotBlank
    private String cep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep(){
        return cep;
    }
}
