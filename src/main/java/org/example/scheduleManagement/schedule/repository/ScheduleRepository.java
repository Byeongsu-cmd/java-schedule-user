package org.example.scheduleManagement.schedule.repository;

import org.example.scheduleManagement.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // List 결과가 여러 개일 수 있는 조회 (전체조회 및 유저 아이디 검증)
    List<Schedule> findByUserId(Long userId);

    // Optional 존재할 수도 있고, 없을 수도 있다. (단건조회, 수정, 삭제 전 검증)
    Optional<Schedule> findByUserIdAndId(Long userId, Long id);
}
