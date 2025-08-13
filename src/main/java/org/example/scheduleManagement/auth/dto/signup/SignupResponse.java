package org.example.scheduleManagement.auth.dto.signup;

import lombok.Getter;

@Getter
public class SignupResponse {
    private final String userName; // 유저

    public SignupResponse(String userName){
        this.userName = userName;
    }
}
