package org.example.scheduleManagement.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.schedule.dto.get.ScheduleGetResponse;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostRequest;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostResponse;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutRequest;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutResponse;
import org.example.scheduleManagement.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/users/{userId}/schedules")
    public ResponseEntity<SchedulePostResponse> schedulePost(
            @RequestBody SchedulePostRequest schedulePostRequest,
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(scheduleService.createSchedule(userId, schedulePostRequest));
    }

    @GetMapping("/users/{userId}/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getSchedules(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(scheduleService.getSchedules(userId));
    }

    @GetMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getSchedule(
            @PathVariable Long userId,
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.ok(scheduleService.getSchedule(userId, scheduleId));
    }

    @PutMapping("/users/{userId}/schedules/{scheduleId}")
    public ResponseEntity<SchedulePutResponse> updateSchedule(
            @RequestBody SchedulePutRequest schedulePutRequest,
            @PathVariable Long userId,
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(userId, scheduleId, schedulePutRequest));
    }

    @DeleteMapping("/users/{userId}/schedules/{scheduleId}")
    public void deleteSchedule(
            @PathVariable Long userId,
            @PathVariable Long scheduleId
    ) {
        scheduleService.deleteSchedule(userId, scheduleId);
    }
}
