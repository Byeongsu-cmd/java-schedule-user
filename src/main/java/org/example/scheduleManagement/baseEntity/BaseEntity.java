package org.example.scheduleManagement.baseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA: 해당 클래스를 상속받는 엔티티들이 이 필드들을 컬럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) // 엔티티 변경 시 생성일, 수정일을 자동으로 기록해주는 리스너 등록
public abstract class BaseEntity {

    @CreatedDate // 데이터 생성 시 현재 시간을 자동으로 저장
    @Column(updatable = false) // 생성일은 한 번 저장되면 수정되지 않도록 설정
    @Temporal(TemporalType.TIMESTAMP) // 시간 타입을 TIMESTAMP로 지정 (날짜+시간)
    private LocalDateTime createTime; // 생성 시간

    @LastModifiedDate // 데이터 수정 시 현재 시간을 자동으로 갱신
    @Temporal(TemporalType.TIMESTAMP) // 시간 타입을 TIMESTAMP로 지정 (날짜+시간)
    private LocalDateTime modifiedTime; // 마지막 수정 시간

}
