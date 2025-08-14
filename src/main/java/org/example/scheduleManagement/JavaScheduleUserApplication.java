package org.example.scheduleManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaScheduleUserApplication { // 스프링 부트의 자동 설정, 컴포넌트 스캔, Bean 등록 기능을 활성화

    public static void main(String[] args) {
        // Spring Boot 애플리케이션을 실행
        SpringApplication.run(JavaScheduleUserApplication.class, args);
    }
}
