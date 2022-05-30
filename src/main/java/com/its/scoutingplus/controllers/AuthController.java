package com.its.scoutingplus.controllers;

import com.its.scoutingplus.repository.entities.Requests.ChangePasswordRequest;
import com.its.scoutingplus.repository.entities.Requests.RegisterUserRequest;
import com.its.scoutingplus.services.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.its.scoutingplus.constants.API_URL;

//Authentication Controller
@RestController
@RequestMapping(value = API_URL + "/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        return authService.register(request.getUsername(), request.getPassword());
    }

    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        return authService.changePassword(request.getUsername(), request.getOldPassword(), request.getNewPassword());
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return authService.refreshToken(request, response);
    }
}
