package com.example.vk_enrollment_project.web.security;

import com.example.vk_enrollment_project.domain.entity.security_user.Account;
import com.example.vk_enrollment_project.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JtwUsersDetailsService implements UserDetailsService {
    private final AccountService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userService.getByUsername(username);
        return JwtEntityFactory.create(user);
    }
}
