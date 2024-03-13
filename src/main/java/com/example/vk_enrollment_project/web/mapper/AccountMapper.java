package com.example.vk_enrollment_project.web.mapper;

import com.example.vk_enrollment_project.domain.entity.security_user.Account;
import com.example.vk_enrollment_project.web.dto.security_user.AccountDto;
import com.example.vk_enrollment_project.web.mapper.abstract_mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiConsumer;

@Component
public class AccountMapper implements Mapper<Account, AccountDto> {

    @Override
    public AccountDto toDto(Account entity) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(entity.getId());
        accountDto.setName(entity.getName());
        accountDto.setUsername(entity.getUsername());
        accountDto.setEmail(entity.getEmail());
        accountDto.setPassword(entity.getPassword());
        return accountDto;
    }

    @Override
    public Account toEntity(AccountDto dto, BiConsumer<Account, AccountDto> block) {
        Account account = new Account();
        account.setName(dto.getName());
        account.setUsername(dto.getUsername());
        account.setEmail(dto.getEmail());
        account.setPassword(dto.getPassword());
        return account;
    }

    @Override
    public List<AccountDto> toDtos(List<Account> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public List<Account> toEntities(List<AccountDto> dtos) {
        return dtos.stream().map(dto -> toEntity(dto, null)).toList();
    }
}
