package org.example.scheduleManagement.schedule.dto.post;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePostResponse {
    private final Long id;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;

    public SchedulePostResponse(Long id, String userName, String title, String content, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
