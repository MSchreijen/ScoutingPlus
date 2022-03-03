package com.its.scoutingplus.services.interfaces;

import com.its.scoutingplus.repository.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    List<User> getUsersByRole(String role);
    int createUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
