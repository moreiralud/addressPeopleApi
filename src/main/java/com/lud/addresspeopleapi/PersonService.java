package com.lud.addresspeopleapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
    public class PersonService {

        private final PersonRepository personRepository;

        @Autowired
        public PersonService(PersonRepository personRepository) {
            this.personRepository = personRepository;
        }

        public boolean createAddressForPerson(Long personId, Address newAddress) {
            Optional<Person> optionalPerson = personRepository.findById(personId);

            if (optionalPerson.isPresent()) {
                Person person = optionalPerson.get();
                newAddress.setPerson(person);
                person.getAddresses().add(newAddress);
                personRepository.save(person);
                return true;
            }

            return false;
        }

        public Person createPerson (Person person) {
            person.setAddresses(null);
            return personRepository.save(person);
        }
    }
