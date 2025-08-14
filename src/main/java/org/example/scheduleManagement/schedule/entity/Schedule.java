package org.example.scheduleManagement.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleManagement.baseEntity.BaseEntity;
import org.example.scheduleManagement.user.entity.User;

@Entity
@Getter
@NoArgsConstructor // 기본 생성자(매개변수가 없는 생성자)를 생성
@Table(name = "Schedule") // 테이블 명은 Schedule
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 고유 아이디

//    @Column(name = "user_name", nullable = false) 유저에 이미 유저 이름이 포함되어 있기 때문에 주석 처리
//    private String userName; // 임시 유저 이름

    @Column(name = "title", nullable = false) // null이 포함될 수 없다.
    private String title; // 일정 제목

    @Column(name = "content", nullable = false) // null이 포함될 수 없다.
    private String content; // 일정 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // null이 포함될 수 없다.
    private User user;

//    public Schedule(String userName, String title, String content, User user) {
//        this.userName = userName;
//        this.title = title;
//        this.content = content;
//        this.user = user;
//    }

    // 유저명 수정 후
    public Schedule(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateSchedule(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
