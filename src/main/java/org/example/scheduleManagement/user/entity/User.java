package org.example.scheduleManagement.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleManagement.baseEntity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "User")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void updateUser(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
