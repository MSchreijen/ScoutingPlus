package com.its.scoutingplus.repository.entities.Requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;
}
