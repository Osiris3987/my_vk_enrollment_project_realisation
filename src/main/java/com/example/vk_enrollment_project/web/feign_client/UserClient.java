package com.example.vk_enrollment_project.web.feign_client;

import com.example.vk_enrollment_project.config.feign_client_config.UserClientExceptionHandler;
import com.example.vk_enrollment_project.config.feign_client_config.HandleFeignError;
import com.example.vk_enrollment_project.web.dto.album.AlbumDto;
import com.example.vk_enrollment_project.web.dto.post.PostDto;
import com.example.vk_enrollment_project.web.dto.todo.TodoDto;
import com.example.vk_enrollment_project.web.dto.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "users", url = "https://jsonplaceholder.typicode.com/users")
public interface UserClient {

    @GetMapping
    @HandleFeignError(UserClientExceptionHandler.class)
    List<UserDto> getUsers();

    @GetMapping("/{id}")
    @HandleFeignError(UserClientExceptionHandler.class)
    UserDto getUser(@PathVariable Long id);

    @GetMapping("/{id}/albums")
    @HandleFeignError(UserClientExceptionHandler.class)
    List<AlbumDto> getAlbums(@PathVariable Long id);

    @GetMapping("/{id}/todos")
    @HandleFeignError(UserClientExceptionHandler.class)
    List<TodoDto> getTodos(@PathVariable Long id);

    @GetMapping("/{id}/posts")
    @HandleFeignError(UserClientExceptionHandler.class)
    List<PostDto> getPosts(@PathVariable Long id);

    @PostMapping
    @HandleFeignError(UserClientExceptionHandler.class)
    UserDto createUser(@RequestBody UserDto user);

    @PutMapping("/{id}")
    @HandleFeignError(UserClientExceptionHandler.class)
    UserDto updateUser(@PathVariable Long id, @RequestBody UserDto user);

    @PatchMapping("/{id}")
    @HandleFeignError(UserClientExceptionHandler.class)
    UserDto patchUser(@PathVariable Long id, @RequestBody UserDto user);

    @DeleteMapping("/{id}")
    @HandleFeignError(UserClientExceptionHandler.class)
    void deleteUser(@PathVariable Long id);
}
