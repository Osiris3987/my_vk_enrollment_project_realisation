package com.example.vk_enrollment_project.service;

import com.example.vk_enrollment_project.domain.audit.DomainType;
import com.example.vk_enrollment_project.domain.audit.RequestType;

public interface AuditLogService {
    void create(String requestParams, String username, RequestType requestType, DomainType domain);
}
