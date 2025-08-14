package org.example.scheduleManagement.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.schedule.dto.delete.ScheduleDeleteRequest;
import org.example.scheduleManagement.schedule.dto.get.ScheduleGetResponse;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostRequest;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostResponse;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutRequest;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutResponse;
import org.example.scheduleManagement.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Controller + ResponseBody
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동 생성
@RequestMapping("/users") // 이 컨트롤러의 기본 URL "/users"로 지정
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 등록 주소(Post) http://localhost:8080/users/{userId}/schedules
    @PostMapping("/{userId}/schedules")
    public ResponseEntity<SchedulePostResponse> schedulePost(
            @RequestBody SchedulePostRequest schedulePostRequest,
            @PathVariable Long userId // URL 경로에서 사용자 ID를 입력 받는다.
    ) {
        return ResponseEntity.ok(scheduleService.createSchedule(userId, schedulePostRequest));
    }

    // 모든 일정 조회 or 특정 사용자(userId)의 일정만 조회
    // 주소(Get) http://localhost:8080/users/{userId}/schedules
    // OR http://localhost:8080/users/users/{userId}/schedules?userId = ”userId”
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getSchedules(
            @RequestParam(required = false) Long userId // 유저 아이디가 있을 수도 있고 없을 수도 있다.(null 가능)
    ) {
        // userId가 있으면 해당 유저 일정만, 없으면 전체 일정 조회
        return ResponseEntity.ok(scheduleService.getSchedules(userId));
    }

    // 일정 선택 조회 주소(Get) http://localhost:8080/users/{userId}/schedules/{scheduleId}
    @GetMapping("/{userId}/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getSchedule(
            @PathVariable Long userId, // URL 경로에서 사용자 ID를 입력 받는다.
            @PathVariable Long scheduleId // URL 경로에서 일정 ID를 입력 받는다.
    ) {
        return ResponseEntity.ok(scheduleService.getSchedule(userId, scheduleId));
    }

    // 일정 수정 주소(Put) http://localhost:8080/users/{userId}/schedules/{scheduleId}
    @PutMapping("/{userId}/schedules/{scheduleId}")
    public ResponseEntity<SchedulePutResponse> updateSchedule(
            @PathVariable Long userId, // URL 경로에서 사용자 ID를 입력 받는다.
            @PathVariable Long scheduleId, // URL 경로에서 일정 ID를 입력 받는다.
            @RequestBody SchedulePutRequest schedulePutRequest
            ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(userId, scheduleId, schedulePutRequest));
    }

    // 일정 삭제 주소(Post) http://localhost:8080/users/{userId}/schedules/{scheduleId}
    @PostMapping("/{userId}/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long userId, // URL 경로에서 사용자 ID를 입력 받는다.
            @PathVariable Long scheduleId, // URL 경로에서 일정 ID를 입력 받는다.
            @RequestBody ScheduleDeleteRequest scheduleDeleteRequest
    ) {
        scheduleService.deleteSchedule(userId, scheduleId, scheduleDeleteRequest);
        return ResponseEntity.ok().build();
    }
}
