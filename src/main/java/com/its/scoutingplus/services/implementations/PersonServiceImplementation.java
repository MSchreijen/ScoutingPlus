package com.its.scoutingplus.services.implementations;

import com.its.scoutingplus.repository.entities.Person;
import com.its.scoutingplus.repository.interfaces.PersonRepository;
import com.its.scoutingplus.services.interfaces.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImplementation implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImplementation(@Qualifier("fake") PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.getPersonById(id);
    }

    @Override
    public List<Person> getPersonsByName(String firstName) {
        List<Person> filteredPersons = new ArrayList<>();

        for (Person person : personRepository.getAllPersons()) {
            if(person.getFirstName().contains(firstName)) filteredPersons.add(person);
        }

        return filteredPersons;
    }

    @Override
    public int createPerson(Person person) {
        return personRepository.createPerson(person);
    }

    @Override
    public boolean updatePerson(Person person) {
        return personRepository.updatePerson(person);
    }

    @Override
    public boolean deletePerson(Person person) {
        return personRepository.deletePerson(person);
    }
}
