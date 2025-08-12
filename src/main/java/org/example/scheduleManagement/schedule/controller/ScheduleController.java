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
        return ResponseEntity.ok(scheduleService.createSchedule(schedulePostRequest,userId));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getSchedules() {
        return ResponseEntity.ok(scheduleService.getSchedules());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getSchedule(
            @PathVariable Long scheduleId
    ){
        return ResponseEntity.ok(scheduleService.getSchedule(scheduleId));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<SchedulePutResponse> updateSchedule(
            @RequestBody SchedulePutRequest schedulePutRequest,
            @PathVariable Long scheduleId
    ){
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, schedulePutRequest));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedule(
            @PathVariable Long scheduleId
    ){
        scheduleService.deleteSchedule(scheduleId);
    }
}
