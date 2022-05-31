package com.its.scoutingplus.services.implementations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.its.scoutingplus.repository.entities.Role;
import com.its.scoutingplus.repository.entities.User;
import com.its.scoutingplus.repository.interfaces.RoleRepository;
import com.its.scoutingplus.repository.interfaces.UserRepository;
import com.its.scoutingplus.services.interfaces.AuthService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class AuthServiceImplementation implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthServiceImplementation(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<?> register(String username, String email, String password) {
        if(username.length() > 20) {
            return ResponseEntity.badRequest().body("Username must be less than 20 characters");
        }

        if(username.length() < 4) {
            return ResponseEntity.badRequest().body("Username must be at least 4 characters");
        }

        if(password.length() > 25) {
            return ResponseEntity.badRequest().body("Password must be less than 25 characters");
        }

        if(password.length() < 4) {
            return ResponseEntity.badRequest().body("Password must be at least 4 characters");
        }

        if(userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User(username, email, encoder.encode(password));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok().body("User registered successfully");
    }

    @Override
    public ResponseEntity<?> changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        } else {
            if(!encoder.matches(oldPassword, user.get().getPassword())) {
                return ResponseEntity.badRequest().body("Incorrect password");
            }
            if(newPassword.length() > 25) {
                return ResponseEntity.badRequest().body("Password must be less than 25 characters");
            }
            if(newPassword.length() < 4) {
                return ResponseEntity.badRequest().body("Password must be at least 4 characters");
            }

            user.get().setPassword(encoder.encode(newPassword));
            userRepository.save(user.get());
            return ResponseEntity.ok().body("Password changed successfully");
        }
    }

    @Override
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        String refresh_token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String username = decodedJWT.getSubject();
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isEmpty()) return ResponseEntity.badRequest().body("User not found");
            String access_token = JWT.create()
                    .withSubject(username)
                    .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 60 * 10 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", user.get().getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);
            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            return ResponseEntity.ok().body(tokens);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }
}
