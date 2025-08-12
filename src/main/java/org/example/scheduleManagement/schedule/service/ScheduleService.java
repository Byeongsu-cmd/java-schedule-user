package org.example.scheduleManagement.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
}
