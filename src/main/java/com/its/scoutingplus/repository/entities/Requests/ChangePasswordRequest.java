package com.its.scoutingplus.repository.entities.Requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class ChangePasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
