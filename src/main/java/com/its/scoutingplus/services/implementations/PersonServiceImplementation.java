package com.its.scoutingplus.services.implementations;

import com.its.scoutingplus.repository.entities.Person;
import com.its.scoutingplus.repository.interfaces.PersonRepository;
import com.its.scoutingplus.services.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImplementation implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImplementation(@Qualifier("fake") PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return person.get();
        }
        else {
            return null;
        }
    }

    @Override
    public List<Person> getPersonsByName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    @Override
    public Long createPerson(Person person) {
        return personRepository.save(person).getId();
    }

    @Override
    public boolean updatePerson(Person person) {
        if (personRepository.existsById(person.getId())) {
            personRepository.save(person);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deletePerson(Person person) {
        if (personRepository.existsById(person.getId())) {
            personRepository.delete(person);
            return true;
        }
        else {
            return false;
        }
    }
}
