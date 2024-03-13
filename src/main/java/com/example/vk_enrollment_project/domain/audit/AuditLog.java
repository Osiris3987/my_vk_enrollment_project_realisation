package com.example.vk_enrollment_project.domain.audit;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@Data
@NoArgsConstructor
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    private String username;

    @JdbcTypeCode(SqlTypes.JSON)
    private  String requestParams;

    @Enumerated(value = EnumType.STRING)
    private RequestType requestType;

    @Enumerated(value = EnumType.STRING)
    private DomainType domain;

    public AuditLog(String username, String requestParams, RequestType requestType, DomainType domain) {
        this.dateTime = LocalDateTime.now();
        this.username = username;
        this.requestParams = requestParams;
        this.requestType = requestType;
        this.domain = domain;
    }
}
