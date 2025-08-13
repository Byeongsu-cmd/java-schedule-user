package org.example.scheduleManagement.auth.dto.login;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String userName;

    public LoginResponse(String userName) {
        this.userName = userName;
    }
}
