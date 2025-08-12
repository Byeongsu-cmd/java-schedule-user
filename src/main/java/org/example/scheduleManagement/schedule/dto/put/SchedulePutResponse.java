package org.example.scheduleManagement.schedule.dto.put;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePutResponse {
    private final Long id;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;

    public SchedulePutResponse(
            Long id,
            String userName,
            String title,
            String content,
            LocalDateTime createTime,
            LocalDateTime updateTime
    ) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}