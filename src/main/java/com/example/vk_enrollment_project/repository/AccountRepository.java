package com.example.vk_enrollment_project.repository;

import com.example.vk_enrollment_project.domain.entity.security_user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
