package com.its.scoutingplus.controllers;

import com.its.scoutingplus.repository.entities.Person;
import com.its.scoutingplus.services.interfaces.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Person>> getAllPersons() {
        try{
            return ResponseEntity.ok(personService.getAllPersons());
        }
        catch (Exception e) {
            logger.error("An error was thrown in getAllPersons!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(personService.getPersonById(id));
        }
        catch (Exception e) {
            logger.error("An error was thrown in getPersonById!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        try {
            int id = personService.createPerson(person);
            URI location = new URI("/person/" + id);
            return ResponseEntity.created(location).build();
        }
        catch (Exception e) {
            logger.error("An error was thrown in createPerson!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<?> updatePerson(@RequestBody Person person) {
        try {
            if (personService.updatePerson(person)) return ResponseEntity.noContent().build();
            else return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            logger.error("An error was thrown in updatePerson!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(consumes = "application/json")
    public ResponseEntity<?> deletePerson(@RequestBody Person person) {
        try {
            if (personService.deletePerson(person)) return ResponseEntity.noContent().build();
            else return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            logger.error("An error was thrown in deletePerson!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}