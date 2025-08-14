package org.example.scheduleManagement.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.user.dto.delete.UserDeleteRequest;
import org.example.scheduleManagement.user.dto.get.UserGetResponse;
import org.example.scheduleManagement.user.dto.put.UserPutRequest;
import org.example.scheduleManagement.user.dto.put.UserPutResponse;
import org.example.scheduleManagement.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Controller + ResponseBody
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동 생성
@RequestMapping("/users") // 공통 주소
public class UserController {
    private final UserService userService;

//    // 유저 등록 // 회원가입으로 유저를 생성하기 때문에 유저 등록 구현들은 주석 처리
//    @PostMapping()
//    public ResponseEntity<UserPostResponse> createUser(
//            @RequestBody UserPostRequest userPostRequest
//    ) {
//        return ResponseEntity.ok(userService.createUser(userPostRequest));
//    }

    // 유저 전체 조회 // 주소(Get) http://localhost:8080/users
    @GetMapping()
    public ResponseEntity<List<UserGetResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    // 선택 유저 조회 // 주소(Get) http://localhost:8080/users/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<UserGetResponse> getUserById(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    // 유저 정보 수정 // 주소(Put) http://localhost:8080/users/{userId}
    @PutMapping("/{userId}")
    public ResponseEntity<UserPutResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UserPutRequest userPutRequest
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, userPutRequest));
    }

    // 유저 정보 삭제 // 주소(Post) http://localhost:8080/users/{userId}
    @PostMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId,
            @RequestBody UserDeleteRequest userDeleteRequest
    ) {
        userService.deleteUser(userId, userDeleteRequest);
        return ResponseEntity.ok().build();
    }
}
