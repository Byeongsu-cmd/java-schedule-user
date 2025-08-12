package org.example.scheduleManagement.user.dto.get;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserGetResponse {
    private final Long userId;
    private final String userName;
    private final String email;
    private final LocalDateTime createTime;
    private final LocalDateTime modifiedTime;

    public UserGetResponse(
            Long userId,
            String userName,
            String email,
            LocalDateTime createTime,
            LocalDateTime modifiedTime
    ) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }
}
