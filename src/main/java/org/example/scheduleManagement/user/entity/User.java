package org.example.scheduleManagement.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleManagement.baseEntity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "User") // 테이블 명은 User
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 유저 고유 아이디

    @Column(name = "user_name")
    private String userName; // 유저명

    @Column(name = "email")
    private String email; // 이메일

    @Column(name = "password")
    private String password; // 비밀번호

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    // 수정할 때 사용할 생성자
    public void updateUser(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}