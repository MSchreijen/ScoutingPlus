package com.its.scoutingplus.services.interfaces;

import com.its.scoutingplus.repository.entities.Role;
import com.its.scoutingplus.repository.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    Long createUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
    Long saveRole(Role role);
    ResponseEntity<?> addRoleToUser(String username, String roleName);
}
