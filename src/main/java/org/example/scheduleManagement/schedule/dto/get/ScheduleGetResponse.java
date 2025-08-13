package org.example.scheduleManagement.schedule.dto.get;

import lombok.Getter;
import org.example.scheduleManagement.user.dto.scheduleDto.ScheduleUserResponse;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetResponse {
    private final ScheduleUserResponse scheduleUserResponse;
    private final Long id;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;

    public ScheduleGetResponse(
            ScheduleUserResponse scheduleUserResponse,
            Long id,
            String userName,
            String title,
            String content,
            LocalDateTime createTime,
            LocalDateTime updateTime
    ) {
        this.scheduleUserResponse = scheduleUserResponse;
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
