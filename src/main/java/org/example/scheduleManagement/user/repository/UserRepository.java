package org.example.scheduleManagement.user.repository;


import org.example.scheduleManagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);

    // 이메일이 존재할 수도 있으니 Optional
    Optional<User> findByEmail(String email);
}
