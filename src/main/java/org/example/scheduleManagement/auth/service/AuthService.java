package org.example.scheduleManagement.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.auth.dto.login.LoginRequest;
import org.example.scheduleManagement.auth.dto.login.LoginResponse;
import org.example.scheduleManagement.auth.dto.signup.SignupRequest;
import org.example.scheduleManagement.auth.dto.signup.SignupResponse;
import org.example.scheduleManagement.user.entity.User;
import org.example.scheduleManagement.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    // 회원가입
    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("이미 가입하신 이메일 입니다.");
        }
        User user = new User(
                signupRequest.getUserName(),
                signupRequest.getEmail(),
                signupRequest.getPassword()
        );
        userRepository.save(user);
        return new SignupResponse(user.getUserName());
    }

    // 로그인
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        return new LoginResponse(user.getUserName());
    }
}


