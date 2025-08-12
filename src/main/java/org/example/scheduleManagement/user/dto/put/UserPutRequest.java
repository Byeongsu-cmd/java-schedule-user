package org.example.scheduleManagement.user.dto.put;

import lombok.Getter;

@Getter
public class UserPutRequest {
    private String userName;
    private String email;
    private String password;
}
