package org.example.scheduleManagement.user.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.user.dto.get.UserGetResponse;
import org.example.scheduleManagement.user.dto.post.UserPostRequest;
import org.example.scheduleManagement.user.dto.post.UserPostResponse;
import org.example.scheduleManagement.user.dto.put.UserPutRequest;
import org.example.scheduleManagement.user.dto.put.UserPutResponse;
import org.example.scheduleManagement.user.entity.User;
import org.example.scheduleManagement.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 유저 등록
    @Transactional
    public UserPostResponse createUser(UserPostRequest userPostRequest) {
        User user = new User(
                userPostRequest.getUserName(),
                userPostRequest.getEmail(),
                userPostRequest.getPassword()
        );
        userRepository.save(user);
        return new UserPostResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateTime(),
                user.getModifiedTime()
        );
    }

    // 유저 전체 조회
    @Transactional(readOnly = true)
    public List<UserGetResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserGetResponse> userGetResponses = new ArrayList<>();
        for (User user : users) {
            userGetResponses.add(new UserGetResponse(
                    user.getId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getCreateTime(),
                    user.getModifiedTime()
            ));
        }
        return userGetResponses;
    }

    // 선택 유저 조회
    @Transactional(readOnly = true)
    public UserGetResponse getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow( // 유저 아이디 검증
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        return new UserGetResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateTime(),
                user.getModifiedTime()
        );
    }

    // 유저 정보 수정
    @Transactional
    public UserPutResponse updateUser(Long userId, UserPutRequest userPutRequest) {
        User user = userRepository.findById(userId).orElseThrow( // 유저 아이디 검증
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        user.updateUser(
                userPutRequest.getUserName(),
                userPutRequest.getEmail(),
                userPutRequest.getPassword()
        );
        return new UserPutResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateTime(),
                user.getModifiedTime()
        );
    }

    // 유저 정보 삭제
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow( // 유저 아이디 검증
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        userRepository.deleteById(userId);
    }
}
