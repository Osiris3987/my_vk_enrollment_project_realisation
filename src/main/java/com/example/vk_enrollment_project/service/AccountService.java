package com.example.vk_enrollment_project.service;

import com.example.vk_enrollment_project.domain.entity.security_user.Account;
import com.example.vk_enrollment_project.web.dto.security_user.AccountDto;

public interface AccountService {
    Account create(AccountDto userDto);

    Account getById(Long id);

    Account getByUsername(String username);
}
