package com.example.vk_enrollment_project.service.impl;

import com.example.vk_enrollment_project.domain.entity.security_user.Role;
import com.example.vk_enrollment_project.domain.entity.security_user.Account;
import com.example.vk_enrollment_project.domain.exception.ResourceNotFoundException;
import com.example.vk_enrollment_project.repository.AccountRepository;
import com.example.vk_enrollment_project.service.AccountService;
import com.example.vk_enrollment_project.web.dto.security_user.AccountDto;
import com.example.vk_enrollment_project.web.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Account create(AccountDto userDto) {
        if (accountRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exists.");
        }
        Account account = accountMapper.toEntity(userDto, null);
        account.setRoles(Set.of(Role.AUTHORIZED));
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Account getByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


}
