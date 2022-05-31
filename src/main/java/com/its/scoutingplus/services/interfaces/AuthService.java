package com.its.scoutingplus.services.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    ResponseEntity<?> register(String username, String email, String password);
    ResponseEntity<?> changePassword(String username, String oldPassword, String newPassword);

    ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response);
}
