package org.example.scheduleManagement.auth.dto.signup;

import lombok.Getter;

@Getter
public class SignupRequest {
    private String userName;
    private String email;
    private String password;
}
