package org.example.scheduleManagement.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleManagement.baseEntity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 아이디
    private String userName; // 임시 유저 이름
    private String title; // 일정 제목
    private String content; // 일정 내용

    public Schedule(String userName,String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    public void updateSchedule(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
