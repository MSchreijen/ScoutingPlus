package com.its.scoutingplus.repository.implementations;

import com.its.scoutingplus.repository.entities.User;
import com.its.scoutingplus.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Qualifier("fake")
@Repository
public class FakeUserData implements UserRepository {

    private final List<User> userList;

    public FakeUserData() {
        this.userList = new ArrayList<>();
        userList.add(new User(1, "merijn", "test", "admin"));
        userList.add(new User(2, "testuser1", "test", "user"));
        userList.add(new User(3, "testuser2", "test", "user"));
    }

    @Override
    public List<User> getAllUsers() {
        return userList.stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public User getUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    @Override
    public int createUser(User obj) {
        int newId = userList.size();
        obj.setId(newId);
        userList.add(obj);
        return newId;
    }

    @Override
    public boolean updateUser(User obj) {
        User old = getUserById(obj.getId());
        if(old == obj || old == null) return false;

        old.setUsername(obj.getUsername());
        old.setPassword(obj.getPassword());
        old.setRole(obj.getRole());

        return true;
    }

    @Override
    public boolean deleteUser(User obj) {
        User old = getUserById(obj.getId());
        if (old == null) return false;

        userList.remove(old);
        return true;
    }
}