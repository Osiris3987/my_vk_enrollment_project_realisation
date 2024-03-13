package com.example.vk_enrollment_project.web.controller;

import com.example.vk_enrollment_project.domain.audit.DomainType;
import com.example.vk_enrollment_project.domain.audit.RequestType;
import com.example.vk_enrollment_project.service.AuditLogService;
import com.example.vk_enrollment_project.web.dto.album.AlbumDto;
import com.example.vk_enrollment_project.web.dto.post.PostDto;
import com.example.vk_enrollment_project.web.dto.todo.TodoDto;
import com.example.vk_enrollment_project.web.dto.user.UserDto;
import com.example.vk_enrollment_project.web.feign_client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("@customSecurityExpression.hasUsersAccessRole()")
public class UserController {
    private final UserClient userClient;
    private final AuditLogService auditLogService;

    @GetMapping
    public List<UserDto> getUsers(@AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(null, userDetails.getUsername(), RequestType.GET, DomainType.USERS);
        return userClient.getUsers();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "UserClient::getUser", key = "#id")
    public UserDto getUser(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.GET, DomainType.USERS);
        return userClient.getUser(id);
    }

    @GetMapping("/{id}/albums")
    public List<AlbumDto> getAlbums(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.GET, DomainType.USERS);
        return userClient.getAlbums(id);
    }

    @GetMapping("/{id}/todos")
    public List<TodoDto> getTodos(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.GET, DomainType.USERS);
        return userClient.getTodos(id);
    }

    @GetMapping("/{id}/posts")
    public List<PostDto> getPosts(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.GET, DomainType.USERS);
        return userClient.getPosts(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(user.toString(), userDetails.getUsername(), RequestType.POST, DomainType.USERS);
        return userClient.createUser(user);
    }

    @PutMapping("/{id}")
    @CachePut(value = "UserClient::getUser", key = "#id")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto user, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(user.toString(), userDetails.getUsername(), RequestType.UPDATE, DomainType.USERS);
        return userClient.updateUser(id, user);
    }

    @PatchMapping("/{id}")
    @CachePut(value = "UserClient::getUser", key = "#id")
    public UserDto patchUser(@PathVariable Long id, @RequestBody UserDto user, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(user.toString(), userDetails.getUsername(), RequestType.UPDATE, DomainType.USERS);
        return userClient.patchUser(id, user);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "UserClient::getUser", key = "#id")
    public void deleteUser(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.DELETE, DomainType.USERS);
        userClient.deleteUser(id);
    }
}
