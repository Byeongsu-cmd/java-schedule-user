package org.example.scheduleManagement.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.auth.dto.login.LoginRequest;
import org.example.scheduleManagement.auth.dto.login.LoginResponse;
import org.example.scheduleManagement.auth.dto.signup.SignupRequest;
import org.example.scheduleManagement.auth.dto.signup.SignupResponse;
import org.example.scheduleManagement.user.entity.User;
import org.example.scheduleManagement.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor  // final 필드에 대한 생성자를 자동 생성
public class AuthService {
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {
        // 이메일 중복 체크
        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        // User 엔티티 생성
        User user = new User(
                signupRequest.getUserName(),
                signupRequest.getEmail(),
                signupRequest.getPassword()
        );
        // DB 저장
        userRepository.save(user);
        return new SignupResponse(user.getUserName());
    }

    // 로그인
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        // 이메일과 비밀번호로 유저 검색 (없으면 401 예외)
        User user = userRepository.findByEmailAndPassword(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        return new LoginResponse(user.getId(), user.getUserName());
    }
}


