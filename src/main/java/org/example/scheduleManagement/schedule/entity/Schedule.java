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
    private String title; // 일정 제목
    private String content; // 일정 내용

    public Schedule(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
