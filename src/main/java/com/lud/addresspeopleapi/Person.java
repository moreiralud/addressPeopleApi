package com.lud.addresspeopleapi;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @Past(message = "A data de nascimento deve estar no passado")
    private LocalDate birthDate;

    @Pattern(regexp = "^(M|F|O)$", message = "O gênero deve ser M, F ou O")
    private String gender;

    @NotBlank(message = "O telefone não pode estar em branco")
    @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
    private String phoneNumber;

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "O e-mail deve ser válido")
    private String email;

//    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "person_addresses", joinColumns = @JoinColumn(name = "person_id"))
    private Set<Address> addresses = new HashSet<>();

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Address> getAddresses() {
        return  addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
