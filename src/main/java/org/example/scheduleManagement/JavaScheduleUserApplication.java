package org.example.scheduleManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JavaScheduleUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaScheduleUserApplication.class, args);
    }
}
