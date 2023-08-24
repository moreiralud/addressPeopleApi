package com.lud.addresspeopleapi;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "a rua não pode estar em branco")
    private String street;
    @Column
    @NotBlank(message = "o bairro não pode estar em branco")
    @Size(max = 100, message = "O bairro não pode ter mais de 30 caracteres")
    private String district;
    @Column
    @NotBlank(message = "a cidade não pode estar em branco")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "A cidade deve conter apenas letras e espaços")
    private String city;
    @NotBlank(message = "O CEP não pode estar em branco")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 12345-678")
    private String cep;
    @NotBlank(message = "O número da residência não pode estar em branco")
    private String houseNumber;

    @NotBlank(message = "O estado não pode estar em branco")
    private String state;

    @NotBlank(message = "O país não pode estar em branco")
    private String country;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

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

    public void setCep(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
