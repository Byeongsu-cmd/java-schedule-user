package org.example.scheduleManagement.user.dto.put;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserPutResponse {
    private final Long userId;
    private final String userName;
    private final String email;
    private final LocalDateTime createTime;
    private final LocalDateTime modifiedTime;

    public UserPutResponse(
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
