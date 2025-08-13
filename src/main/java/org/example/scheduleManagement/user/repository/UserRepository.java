package org.example.scheduleManagement.user.repository;


import org.example.scheduleManagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
