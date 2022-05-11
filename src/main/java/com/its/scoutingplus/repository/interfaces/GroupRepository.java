package com.its.scoutingplus.repository.interfaces;

import com.its.scoutingplus.repository.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByNameContaining(String name);
}
