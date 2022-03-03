package com.its.scoutingplus.repository.implementations;

import com.its.scoutingplus.repository.entities.Person;
import com.its.scoutingplus.repository.interfaces.PersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Qualifier("fake")
@Repository
public class FakePersonData implements PersonRepository {

    private final List<Person> personList;

    public FakePersonData() {
        this.personList = new ArrayList<>();
        personList.add(new Person(1, "Mark", "Stevens"));
        personList.add(new Person(2, "Jack", "Clarkson"));
    }

    @Override
    public List<Person> getAllPersons() {
        return personList.stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Person getPersonById(int id) {
        for (Person person : personList) {
            if (person.getId() == id) return person;
        }
        return null;
    }

    @Override
    public int createPerson(Person obj) {
        int newId = personList.size();
        obj.setId(newId);
        personList.add(obj);
        return newId;
    }

    @Override
    public boolean updatePerson(Person obj) {
        Person old = getPersonById(obj.getId());
        if (old == obj || old == null) return false;

        old.setFirstName(obj.getFirstName());
        old.setLastName(obj.getLastName());

        return true;
    }

    @Override
    public boolean deletePerson(Person obj) {
        Person old = getPersonById(obj.getId());
        if(old == null) return false;

        personList.remove(old);
        return true;
    }
}