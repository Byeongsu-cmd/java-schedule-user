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

@RestController
@RequiredArgsConstructor
@RequestMapping("/users") // 공통 주소
public class UserController {
    private final UserService userService;

//    // 유저 등록
//    @PostMapping()
//    public ResponseEntity<UserPostResponse> createUser(
//            @RequestBody UserPostRequest userPostRequest
//    ) {
//        return ResponseEntity.ok(userService.createUser(userPostRequest));
//    }

    // 유저 전체 조회
    @GetMapping()
    public ResponseEntity<List<UserGetResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    // 선택 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserGetResponse> getUserById(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    // 유저 정보 수정
    @PutMapping("/{userId}")
    public ResponseEntity<UserPutResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UserPutRequest userPutRequest
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, userPutRequest));
    }

    // 유저 정보 삭제
    @PostMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId,
            @RequestBody UserDeleteRequest userDeleteRequest
    ) {
        userService.deleteUser(userId, userDeleteRequest);
        return ResponseEntity.ok().build();
    }
}
