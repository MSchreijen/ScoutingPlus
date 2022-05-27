package com.its.scoutingplus.services.interfaces;

import com.its.scoutingplus.repository.entities.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    Group getGroupById(Long id);
    List<Group> getGroupsByName(String name);
    Long createGroup(Group group);
    boolean updateGroup(Group group);
    boolean deleteGroupById(Long id);
    boolean deleteGroup(Group group);
}