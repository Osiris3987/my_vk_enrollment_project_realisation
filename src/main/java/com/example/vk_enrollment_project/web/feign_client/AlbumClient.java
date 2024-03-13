package com.example.vk_enrollment_project.web.feign_client;

import com.example.vk_enrollment_project.config.feign_client_config.AlbumClientExceptionHandler;
import com.example.vk_enrollment_project.config.feign_client_config.HandleFeignError;
import com.example.vk_enrollment_project.config.feign_client_config.UserClientExceptionHandler;
import com.example.vk_enrollment_project.web.dto.album.AlbumDto;
import com.example.vk_enrollment_project.web.dto.photo.PhotoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "albums", url = "https://jsonplaceholder.typicode.com/albums")
public interface AlbumClient {
    @GetMapping
    @HandleFeignError(AlbumClientExceptionHandler.class)
    List<AlbumDto> getAlbums();

    @GetMapping("/{id}")
    @HandleFeignError(AlbumClientExceptionHandler.class)
    AlbumDto getAlbum(@PathVariable Long id);

    @GetMapping("/{id}/photos")
    @HandleFeignError(AlbumClientExceptionHandler.class)
    List<PhotoDto> getPhotos(@PathVariable Long id);

    @PostMapping
    @HandleFeignError(AlbumClientExceptionHandler.class)
    AlbumDto createAlbum(@RequestBody AlbumDto album);

    @PutMapping("/{id}")
    @HandleFeignError(AlbumClientExceptionHandler.class)
    AlbumDto updateAlbum(@PathVariable Long id, @RequestBody AlbumDto album);

    @PatchMapping("/{id}")
    @HandleFeignError(AlbumClientExceptionHandler.class)
    AlbumDto patchAlbum(@PathVariable Long id, @RequestBody AlbumDto album);

    @DeleteMapping("/{id}")
    @HandleFeignError(AlbumClientExceptionHandler.class)
    void deleteAlbum(@PathVariable Long id);
}
