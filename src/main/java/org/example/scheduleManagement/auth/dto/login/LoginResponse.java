package org.example.scheduleManagement.auth.dto.login;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final Long userId; // 로그인하고 유저 고유 아이디 표시하여 기능을 잘 점검할 수 있게 수정
    private final String userName;

    public LoginResponse(Long userId,String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
