package org.example.scheduleManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 해당 클래스가 스프링 설정 클래스임을 나타냄
@EnableJpaAuditing // JPA 감사(Auditing) 기능 설정
public class JpaConfig {
}
