package com.example.vk_enrollment_project.web.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {
    private Long id;
    private String name;
    private String username;
    private String accessToken;
    private String refreshToken;
}
