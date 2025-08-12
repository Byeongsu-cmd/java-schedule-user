package org.example.scheduleManagement.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleManagement.baseEntity.BaseEntity;
import org.example.scheduleManagement.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 아이디
    private String userName; // 임시 유저 이름
    private String title; // 일정 제목
    private String content; // 일정 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    public Schedule(String userName,String title, String content, User user) {
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateSchedule(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
