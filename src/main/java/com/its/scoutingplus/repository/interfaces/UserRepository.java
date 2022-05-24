package com.its.scoutingplus.repository.interfaces;

import com.its.scoutingplus.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(String role);
}
