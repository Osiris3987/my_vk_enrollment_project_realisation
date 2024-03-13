package com.example.vk_enrollment_project.repository;

import com.example.vk_enrollment_project.domain.audit.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
