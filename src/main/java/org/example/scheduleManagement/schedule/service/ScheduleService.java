package org.example.scheduleManagement.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.schedule.dto.delete.ScheduleDeleteRequest;
import org.example.scheduleManagement.schedule.dto.get.ScheduleGetResponse;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostRequest;
import org.example.scheduleManagement.schedule.dto.post.SchedulePostResponse;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutRequest;
import org.example.scheduleManagement.schedule.dto.put.SchedulePutResponse;
import org.example.scheduleManagement.schedule.entity.Schedule;
import org.example.scheduleManagement.schedule.repository.ScheduleRepository;
import org.example.scheduleManagement.user.dto.scheduleDto.ScheduleUserResponse;
import org.example.scheduleManagement.user.entity.User;
import org.example.scheduleManagement.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 유저명 수정 전
//    // 일정 등록
//    @Transactional
//    public SchedulePostResponse createSchedule(Long userId, SchedulePostRequest schedulePostRequest) {
//        User user = userRepository.findById(userId).orElseThrow( // 유저 아이디 검증 로직
//                () -> new IllegalArgumentException("유저를 찾을 수 없습니다.") // 예외 처리
//        );
//        Schedule schedule = new Schedule(
//                schedulePostRequest.getUserName(),
//                schedulePostRequest.getTitle(),
//                schedulePostRequest.getContent(),
//                user // 유저 아이디
//        );
//        ScheduleUserResponse scheduleUserResponse = new ScheduleUserResponse(
//                user.getId(),
//                user.getUserName(),
//                user.getEmail()
//        );
//        scheduleRepository.save(schedule);
//        return new SchedulePostResponse(
//                scheduleUserResponse,
//                schedule.getId(),
//                schedule.getUserName(),
//                schedule.getTitle(),
//                schedule.getContent(),
//                schedule.getCreateTime(),
//                schedule.getModifiedTime()
//        );
//    }
    // 유저명 제외 수정 후
    // 일정 등록
    @Transactional
    public SchedulePostResponse createSchedule(Long userId, SchedulePostRequest schedulePostRequest) {
        User user = userRepository.findById(userId).orElseThrow( // 유저 아이디 검증 로직
                () -> new IllegalArgumentException("유저를 찾을 수 없습니다.") // 예외 처리
        );
        Schedule schedule = new Schedule(
//                schedulePostRequest.getUserName(),
                schedulePostRequest.getTitle(),
                schedulePostRequest.getContent(),
                user // 유저 아이디
        );
        ScheduleUserResponse scheduleUserResponse = new ScheduleUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
        scheduleRepository.save(schedule);
        return new SchedulePostResponse(
                scheduleUserResponse,
                schedule.getId(),
//                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreateTime(),
                schedule.getModifiedTime()
        );
    }

    // 일정 전체 조회
    public List<ScheduleGetResponse> getSchedules(Long userId) {
        // 일정 전체 조회 로직
        List<Schedule> schedules = scheduleRepository.findAll();

        if (userId != null) { // 만약 유저 아이디의 값이 있다면...
            schedules = scheduleRepository.findByUserId(userId); // 일정 저장소에 유저 아이디로 조회

            User user = userRepository.findById(userId).orElseThrow( // 유저 아이디 검증 로직
                    () -> new IllegalArgumentException("유저를 찾을 수 없습니다.") // 예외 처리
            );
        }

        // 출력을 타입이 List<ScheduleGetResponse>이니 출력 형태를 맞추기위해 새로운 리스트 생성
        // 향상된 for 문을 사용하여 DB에 저장되어 있는 데이터들을 일정 등록 리스폰스에 담아서 출력한다.
        List<ScheduleGetResponse> scheduleGetResponses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            User user = schedule.getUser();
            ScheduleUserResponse scheduleUserResponse = new ScheduleUserResponse(
                    user.getId(),
                    user.getUserName(),
                    user.getEmail()
            );
            scheduleGetResponses.add(new ScheduleGetResponse(
                    scheduleUserResponse,
                    schedule.getId(),
//                    schedule.getUserName(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreateTime(),
                    schedule.getModifiedTime()
            ));
        }
        return scheduleGetResponses;
    }

    // 일정 단건 조회
    public ScheduleGetResponse getSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleRepository.findByUserIdAndId(userId, scheduleId).orElseThrow( // 유저 아이디 검증 로직
                () -> new IllegalArgumentException("해당 일정이 존재하지 않습니다.") // 예외 처리
        );
        User user = schedule.getUser();
        ScheduleUserResponse scheduleUserResponse = new ScheduleUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
        return new ScheduleGetResponse(
                scheduleUserResponse,
                schedule.getId(),
//                schedule.getUserName(),
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
        User user = schedule.getUser();
        ScheduleUserResponse scheduleUserResponse = new ScheduleUserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
        return new SchedulePutResponse(
                scheduleUserResponse,
                schedule.getId(),
//                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreateTime(),
                schedule.getModifiedTime()
        );
    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(Long userId, Long scheduleId, ScheduleDeleteRequest scheduleDeleteRequest) {
        Schedule schedule = scheduleRepository.findByUserIdAndId(userId, scheduleId).orElseThrow( // 유저 아이디 검증 로직
                () -> new IllegalArgumentException("해당 일정이 존재하지 않습니다.") // 예외 처리
        );

        User user = schedule.getUser();
        // 비밀번호의 입력 값이 null이 아닐 때
        if (scheduleDeleteRequest.getPassword() != null) {
            // 입력한 비밀번호가 저장된 비밀번호의 값과 다르다면 예외 처리
            if (!scheduleDeleteRequest.getPassword().equals(user.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            // 비밀번호의 값이 null일 경우
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }


        scheduleRepository.deleteById(scheduleId);
    }
}