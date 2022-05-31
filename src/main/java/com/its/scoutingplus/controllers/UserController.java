package com.its.scoutingplus.controllers;

import com.its.scoutingplus.repository.entities.User;
import com.its.scoutingplus.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.its.scoutingplus.constants.API_URL;

@RestController
@RequestMapping(value = API_URL + "/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        }
        catch (Exception e) {
            logger.error("An error was thrown in getAllUsers!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        }
        catch (Exception e) {
            logger.error("An error was thrown in getUserById!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            Long id = userService.createUser(user);
            URI location = new URI("/user/" + id);
            return ResponseEntity.created(location).build();
        }
        catch (Exception e) {
            logger.error("An error was thrown in createUser!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            if (userService.updateUser(user)) return ResponseEntity.noContent().build();
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            logger.error("An error was thrown in updateUser!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            if (userService.deleteUser(id)) return ResponseEntity.noContent().build();
            else return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            logger.error("An error was thrown in deleteUser!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(consumes = "application/json")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        try {
            if (userService.deleteUser(user)) return ResponseEntity.noContent().build();
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            logger.error("An error was thrown in deleteUser!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}