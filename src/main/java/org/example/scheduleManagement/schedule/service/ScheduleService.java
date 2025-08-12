package org.example.scheduleManagement.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.schedule.dto.get.ScheduleGetResponse;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostRequest;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostResponse;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutRequest;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutResponse;
import org.example.scheduleManagement.schedule.entity.Schedule;
import org.example.scheduleManagement.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 일정 등록
    @Transactional
    public SchedulePostResponse createSchedule(SchedulePostRequest schedulePostRequest) {
        Schedule schedule = new Schedule(
                schedulePostRequest.getUserName(),
                schedulePostRequest.getTitle(),
                schedulePostRequest.getContent()
        );
        scheduleRepository.save(schedule);
        return new SchedulePostResponse(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreateTime(),
                schedule.getModifiedTime()
        );
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> getSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleGetResponse> scheduleGetResponses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleGetResponses.add(new ScheduleGetResponse(
                    schedule.getId(),
                    schedule.getUserName(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreateTime(),
                    schedule.getModifiedTime()
            ));
        }
        return scheduleGetResponses;
    }

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public ScheduleGetResponse getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreateTime(),
                schedule.getModifiedTime()
        );
    }

    // 일정 수정
    @Transactional
    public SchedulePutResponse updateSchedule(Long scheduleId, SchedulePutRequest schedulePutRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        schedule.updateSchedule(
                schedulePutRequest.getTitle(),
                schedulePutRequest.getContent()
        );
        return new SchedulePutResponse(
                schedule.getId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreateTime(),
                schedule.getModifiedTime()
        );
    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        scheduleRepository.deleteById(scheduleId);
    }
}
