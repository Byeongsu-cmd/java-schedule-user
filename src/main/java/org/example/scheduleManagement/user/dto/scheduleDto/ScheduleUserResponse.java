package org.example.scheduleManagement.user.dto.scheduleDto;

import lombok.Getter;

@Getter
public class ScheduleUserResponse {
    private final Long userId;
    private final String userName;
    private final String email;

    public ScheduleUserResponse(Long userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }
}
