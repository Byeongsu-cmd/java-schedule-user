package org.example.scheduleManagement.user.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.user.dto.delete.UserDeleteRequest;
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
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

//    // 유저 등록
//    @Transactional
//    public UserPostResponse createUser(UserPostRequest userPostRequest) {
//        User user = new User(
//                userPostRequest.getUserName(),
//                userPostRequest.getEmail(),
//                userPostRequest.getPassword()
//        );
//        userRepository.save(user);
//        return new UserPostResponse(
//                user.getId(),
//                user.getUserName(),
//                user.getEmail(),
//                user.getCreateTime(),
//                user.getModifiedTime()
//        );
//    }

    // 유저 전체 조회
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
        // 비밀번호의 입력 값이 null이 아닐 때
        if (userPutRequest.getPassword() != null) {
            // 입력한 비밀번호가 저장된 비밀번호의 값과 다르다면 예외 처리
            if (!userPutRequest.getPassword().equals(user.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else { // 비밀번호의 값이 null일 경우
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }

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
    public void deleteUser(Long userId, UserDeleteRequest userDeleteRequest) {
        User user = userRepository.findById(userId).orElseThrow( // 유저 아이디 검증
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        // 비밀번호의 입력 값이 null이 아닐 때
        if (userDeleteRequest.getPassword() != null) {
            // 입력한 비밀번호가 저장된 비밀번호의 값과 다르다면 예외 처리
            if (!userDeleteRequest.getPassword().equals(user.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else { // 비밀번호의 값이 null일 경우
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        userRepository.deleteById(userId);
    }
}
