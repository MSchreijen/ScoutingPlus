package com.its.scoutingplus.repository.interfaces;

import com.its.scoutingplus.repository.entities.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPersons();
    Person getPersonById(int id);
    int createPerson(Person obj);
    boolean updatePerson(Person obj);
    boolean deletePerson(Person obj);
}
