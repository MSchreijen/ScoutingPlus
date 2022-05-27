package com.its.scoutingplus.controllers;

import com.its.scoutingplus.repository.entities.Group;
import com.its.scoutingplus.services.interfaces.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


//The REST controller for the group entity
@RestController
@RequestMapping("/group")
public class GroupController {
    //Autowired group service to use the group service
    private final GroupService groupService;

    //Logger to keep track of errors
    private final Logger logger;

    //Constructor for the group controller
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
        this.logger = LoggerFactory.getLogger(GroupController.class);
    }

    //REST methods to get, post, put, and delete groups
    //Get all groups
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Group>> getAllGroups() {
        try {
            logger.info("Getting all groups");
            return ResponseEntity.ok(groupService.getAllGroups());
        }
        catch(Exception e) {
            logger.error("Error getting all groups", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    //Get all groups by ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Group> getGroupById(@PathVariable("id") Long id) {
        try {
            logger.info("Getting group with id: " + id);
            return ResponseEntity.ok(groupService.getGroupById(id));
        }
        catch(Exception e) {
            logger.error("Error getting group with id: " + id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    //Get all groups by name
    @GetMapping(value = "/name/{name}", produces = "application/json")
    public ResponseEntity<List<Group>> getGroupByName(@PathVariable("name") String name) {
        try {
            logger.info("Getting group with name: " + name);
            return ResponseEntity.ok(groupService.getGroupsByName(name));
        }
        catch(Exception e) {
            logger.error("Error getting group with name: " + name, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    //Post a new group from request body
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Group> postGroup(@RequestBody Group group) {
        try {
            logger.info("Posting group: " + group);
            return ResponseEntity.created(new URI("/group/" + groupService.createGroup(group))).build();
        }
        catch(Exception e) {
            logger.error("Error posting group: " + group, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    //Update a group from request body
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Group> putGroup(@RequestBody Group group) {
        try {
            logger.info("Updating group: " + group);
            if(groupService.updateGroup(group)) {
                return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }
        catch(Exception e) {
            logger.error("Error updating group: " + group, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    //Delete a group by ID
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id") Long id) {
        try {
            logger.info("Deleting group with id: " + id);
            if(groupService.deleteGroupById(id)) {
                return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }
        catch(Exception e) {
            logger.error("Error deleting group with id: " + id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    //Delete a group from request body\
    @DeleteMapping(consumes = "application/json")
    public ResponseEntity<?> deleteGroup(@RequestBody Group group) {
        try {
            logger.info("Deleting group: " + group);
            if(groupService.deleteGroup(group)) {
                return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }
        catch(Exception e) {
            logger.error("Error deleting group: " + group, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
