package org.example.scheduleManagement.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleManagement.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
}
