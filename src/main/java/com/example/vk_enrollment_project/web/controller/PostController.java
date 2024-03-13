package com.example.vk_enrollment_project.web.controller;

import com.example.vk_enrollment_project.domain.audit.DomainType;
import com.example.vk_enrollment_project.domain.audit.RequestType;
import com.example.vk_enrollment_project.service.AuditLogService;
import com.example.vk_enrollment_project.web.dto.post.CommentDto;
import com.example.vk_enrollment_project.web.dto.post.PostDto;
import com.example.vk_enrollment_project.web.feign_client.PostClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@PreAuthorize("@customSecurityExpression.hasPostsAccessRole()")
public class PostController {

    private final PostClient postClient;
    private final AuditLogService auditLogService;

    @GetMapping
    public List<PostDto> getPosts(@AuthenticationPrincipal UserDetails userDetails) {
        auditLogService.create(null, userDetails.getUsername(), RequestType.GET, DomainType.POSTS);
        return postClient.getPosts();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "PostClient::getPost", key = "#id")
    public PostDto getPost(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.GET, DomainType.POSTS);
        return postClient.getPost(id);
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getCommentsForPost(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.GET, DomainType.POSTS);
        return postClient.getCommentsForPost(id);
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto post, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(post.toString(), userDetails.getUsername(), RequestType.POST, DomainType.POSTS);
        return postClient.createPost(post);
    }

    @PutMapping("/{id}")
    @CachePut(value = "PostClient::getPost", key = "#id")
    public PostDto updatePost(@PathVariable Long id, @RequestBody PostDto post, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(post.toString(), userDetails.getUsername(), RequestType.UPDATE, DomainType.POSTS);
        return postClient.updatePost(id, post);
    }

    @PatchMapping("/{id}")
    @CachePut(value = "PostClient::getPost", key = "#id")
    public PostDto patchPost(@PathVariable Long id, @RequestBody PostDto post, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(post.toString(), userDetails.getUsername(), RequestType.UPDATE, DomainType.POSTS);
        return postClient.patchPost(id, post);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "PostClient::getPost", key = "#id")
    public void deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.DELETE, DomainType.POSTS);
        postClient.deletePost(id);
    }

}
