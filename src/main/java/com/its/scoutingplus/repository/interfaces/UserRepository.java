package com.its.scoutingplus.repository.interfaces;

import com.its.scoutingplus.repository.entities.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserById(int id);
    int createUser(User obj);
    boolean updateUser(User obj);
    boolean deleteUser(User obj);
}
