package com.its.scoutingplus.services.interfaces;

import com.its.scoutingplus.repository.entities.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();
    Person getPersonById(int id);
    List<Person> getPersonsByName(String firstName);
    int createPerson(Person person);
    boolean updatePerson(Person person);
    boolean deletePerson(Person person);
}
