package com.its.scoutingplus.services.implementations;

import com.its.scoutingplus.repository.entities.User;
import com.its.scoutingplus.repository.interfaces.UserRepository;
import com.its.scoutingplus.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(@Qualifier("fake") UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        List<User> filteredUsers = new ArrayList<>();

        for (User user : userRepository.getAllUsers()) {
            if(user.getRole().equals(role)) filteredUsers.add(user);
        }

        return filteredUsers;
    }

    @Override
    public int createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return userRepository.deleteUser(user);
    }
}
