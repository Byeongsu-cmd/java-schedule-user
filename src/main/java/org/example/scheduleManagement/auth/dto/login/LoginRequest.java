package org.example.scheduleManagement.auth.dto.login;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
