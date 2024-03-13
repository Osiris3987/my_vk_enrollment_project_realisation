package com.example.vk_enrollment_project.service.impl;

import com.example.vk_enrollment_project.domain.audit.AuditLog;
import com.example.vk_enrollment_project.domain.audit.DomainType;
import com.example.vk_enrollment_project.domain.audit.RequestType;
import com.example.vk_enrollment_project.repository.AuditLogRepository;
import com.example.vk_enrollment_project.service.AuditLogService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogRepository auditLogRepository;

    private final Gson gson = new GsonBuilder().create();

    @Override
    public void create(String requestParams, String username, RequestType requestType, DomainType domain) {
        AuditLog auditLog = new AuditLog(
                username,
                gson.toJson(requestParams),
                requestType,
                domain
        );
        auditLogRepository.save(auditLog);
    }

}
