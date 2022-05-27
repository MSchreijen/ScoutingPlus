package com.its.scoutingplus.repository.interfaces;

import com.its.scoutingplus.repository.entities.Group;
import com.its.scoutingplus.repository.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
}
