package com.its.scoutingplus.services.implementations;

import com.its.scoutingplus.repository.entities.Role;
import com.its.scoutingplus.repository.entities.User;
import com.its.scoutingplus.repository.interfaces.RoleRepository;
import com.its.scoutingplus.repository.interfaces.UserRepository;
import com.its.scoutingplus.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    @Override
    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Long saveRole(Role role) {
        if(roleRepository.findByName(role.getName()).isEmpty()) {
            return roleRepository.save(role).getId();
        }
        else {
            return null;
        }
    }

    @Override
    public ResponseEntity<?> addRoleToUser(String username, String roleName) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(roleName);
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("User not found");
        }
        else if(role.isEmpty()){
            return ResponseEntity.badRequest().body("Role not found");
        }
        else {
            if(user.get().getRoles().stream().anyMatch(c -> c.getName().equals(role.get().getName()))){
                return ResponseEntity
                        .badRequest()
                        .body("User already has this role");
            }
            else {
                user.get().getRoles().add(role.get());
                userRepository.save(user.get());
                return ResponseEntity.ok().body("Role added successfully");
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
        }
    }
}
