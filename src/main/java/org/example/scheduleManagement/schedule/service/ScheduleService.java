package org.example.scheduleManagement.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.schedule.dto.get.ScheduleGetResponse;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostRequest;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostResponse;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutRequest;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutResponse;
import org.example.scheduleManagement.schedule.entity.Schedule;
import org.example.scheduleManagement.schedule.repository.ScheduleRepository;
import org.example.scheduleManagement.user.entity.User;
import org.example.scheduleManagement.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 등록
    @Transactional
    public SchedulePostResponse createSchedule(Long userId, SchedulePostRequest schedulePostRequest) {
        User user = userRepository.findById(userId).orElseThrow( // 유저 아이디 검증 로직
                () -> new IllegalArgumentException("유저를 찾을 수 없습니다.") // 예외 처리
        );
        Schedule schedule = new Schedule(
                schedulePostRequest.getUserName(),
                schedulePostRequest.getTitle(),
                schedulePostRequest.getContent(),
                user // 유저 아이디
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
    public List<ScheduleGetResponse> getSchedules(Long userId) {
        User user = userRepository.findById(userId).orElseThrow( // 유저 아이디 검증 로직
                () -> new IllegalArgumentException("유저를 찾을 수 없습니다.") // 예외 처리
        );

        // 유저 아이디와 일정을 작성한 유저가 같다면 해당 유저의 전체 일정을 조회
        List<Schedule> schedules = scheduleRepository.findByUserId(userId);

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
    public ScheduleGetResponse getSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByUserIdAndId(userId, scheduleId).orElseThrow( // 유저 아이디 검증 로직
                () -> new IllegalArgumentException("해당 일정이 존재하지 않습니다.") // 예외 처리
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
    public SchedulePutResponse updateSchedule(Long userId, Long scheduleId, SchedulePutRequest schedulePutRequest) {
        Schedule schedule = scheduleRepository.findByUserIdAndId(userId, scheduleId).orElseThrow( // 유저 아이디 검증 로직
                () -> new IllegalArgumentException("해당 일정이 존재하지 않습니다.") // 예외 처리
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
    public void deleteSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByUserIdAndId(userId, scheduleId).orElseThrow( // 유저 아이디 검증 로직
                () -> new IllegalArgumentException("해당 일정이 존재하지 않습니다.") // 예외 처리
        );
        scheduleRepository.deleteById(scheduleId);
    }
}