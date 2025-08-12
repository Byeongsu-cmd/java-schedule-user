package org.example.scheduleManagement.schedule.repository;

import org.example.scheduleManagement.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
