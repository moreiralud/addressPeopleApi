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
@RequestMapping("/api/persons")

public class PersonController {
    private final PersonRepository personRepository;
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService, PersonRepository personRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/{personId}/addresses")
    public ResponseEntity<String> createAddressForPerson(@PathVariable Long personId,
                                                         @RequestBody @Valid Address newAddress) {
        boolean addressCreated = personService.createAddressForPerson(personId, newAddress);
        if (addressCreated) {
            return ResponseEntity.ok("Endereço criado com sucesso");
        }
        return ResponseEntity.notFound().build(); // Retorna status 404 se a pessoa não for encontrada
    }
    // ... Other methods ...
}

