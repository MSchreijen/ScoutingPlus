package com.its.scoutingplus.services.implementations;

import com.its.scoutingplus.repository.entities.User;
import com.its.scoutingplus.repository.interfaces.UserRepository;
import com.its.scoutingplus.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public Long createUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public boolean updateUser(User user) {
        if(userRepository.existsById(user.getId())) {
            userRepository.save(user);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        if(userRepository.existsById(user.getId())) {
            userRepository.delete(user);
            return true;
        }
        else {
            return false;
        }
    }
}
