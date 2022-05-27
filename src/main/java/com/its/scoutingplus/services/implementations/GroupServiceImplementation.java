package com.its.scoutingplus.services.implementations;

import com.its.scoutingplus.repository.entities.Group;
import com.its.scoutingplus.repository.interfaces.GroupRepository;
import com.its.scoutingplus.services.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImplementation implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImplementation(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.orElse(null);
    }

    @Override
    public List<Group> getGroupsByName(String name) {
        return groupRepository.findByNameContaining(name);
    }

    @Override
    public Long createGroup(Group group) {
        return groupRepository.save(group).getId();
    }

    @Override
    public boolean updateGroup(Group group) {
        if(groupRepository.existsById(group.getId())) {
            groupRepository.save(group);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteGroupById(Long id) {
        if(groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteGroup(Group group) {
        if(groupRepository.existsById(group.getId())) {
            groupRepository.delete(group);
            return true;
        }
        else {
            return false;
        }
    }
}
