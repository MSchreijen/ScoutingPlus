package com.its.scoutingplus.services.interfaces;

import com.its.scoutingplus.repository.entities.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();
    Person getPersonById(Long id);
    List<Person> getPersonsByName(String firstName);
    Long createPerson(Person person);
    boolean updatePerson(Person person);
    boolean deletePerson(Person person);
}
