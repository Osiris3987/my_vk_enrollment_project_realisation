package com.example.vk_enrollment_project.web.controller;

import com.example.vk_enrollment_project.service.AuthService;
import com.example.vk_enrollment_project.service.AccountService;
import com.example.vk_enrollment_project.web.dto.auth.JwtRequest;
import com.example.vk_enrollment_project.web.dto.auth.JwtResponse;
import com.example.vk_enrollment_project.web.dto.security_user.AccountDto;
import com.example.vk_enrollment_project.web.dto.validation.OnCreate;
import com.example.vk_enrollment_project.web.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public AccountDto register(@Validated(OnCreate.class)
                            @RequestBody final AccountDto accountDto) {
        return accountMapper.toDto(accountService.create(accountDto));
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
