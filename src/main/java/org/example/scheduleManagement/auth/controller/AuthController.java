package org.example.scheduleManagement.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.auth.dto.login.LoginRequest;
import org.example.scheduleManagement.auth.dto.login.LoginResponse;
import org.example.scheduleManagement.auth.dto.signup.SignupRequest;
import org.example.scheduleManagement.auth.dto.signup.SignupResponse;
import org.example.scheduleManagement.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입 - 유저, 이메일, 비밀번호
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(
            @RequestBody SignupRequest signupRequest
    ) {
        authService.signup(signupRequest);
        return ResponseEntity.ok(
                new SignupResponse(
                        signupRequest.getUserName()
                )
        );
    }

    // 로그인 - 이메일, 비밀번호
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request
    ) {
        //
        LoginResponse loginResponse = authService.login(loginRequest);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("LOGIN_USER", loginResponse.getUserName());
        return ResponseEntity.ok(
                new LoginResponse(
                        loginResponse.getUserId(),
                        loginResponse.getUserName()
                )
        );
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            httpSession.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}
