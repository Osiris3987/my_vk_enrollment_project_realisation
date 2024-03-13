package com.example.vk_enrollment_project.web.feign_client;

import com.example.vk_enrollment_project.config.feign_client_config.HandleFeignError;
import com.example.vk_enrollment_project.config.feign_client_config.PostClientExceptionHandler;
import com.example.vk_enrollment_project.web.dto.post.CommentDto;
import com.example.vk_enrollment_project.web.dto.post.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "posts", url = "https://jsonplaceholder.typicode.com/posts")
public interface PostClient {

    @GetMapping
    @HandleFeignError(PostClientExceptionHandler.class)
    List<PostDto> getPosts();

    @GetMapping("/{id}")
    @HandleFeignError(PostClientExceptionHandler.class)
    PostDto getPost(@PathVariable Long id);

    @GetMapping("/{postId}/comments")
    @HandleFeignError(PostClientExceptionHandler.class)
    List<CommentDto> getCommentsForPost(@PathVariable Long postId);

    @PostMapping
    @HandleFeignError(PostClientExceptionHandler.class)
    PostDto createPost(@RequestBody PostDto post);

    @PutMapping("/{id}")
    @HandleFeignError(PostClientExceptionHandler.class)
    PostDto updatePost(@PathVariable Long id, @RequestBody PostDto post);

    @PatchMapping("/{id}")
    @HandleFeignError(PostClientExceptionHandler.class)
    PostDto patchPost(@PathVariable Long id, @RequestBody PostDto post);

    @DeleteMapping("/{id}")
    @HandleFeignError(PostClientExceptionHandler.class)
    void deletePost(@PathVariable Long id);
}
