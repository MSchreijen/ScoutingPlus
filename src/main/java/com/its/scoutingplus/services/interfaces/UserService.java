package com.its.scoutingplus.services.interfaces;

import com.its.scoutingplus.repository.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    List<User> getUsersByRole(String role);
    Long createUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
