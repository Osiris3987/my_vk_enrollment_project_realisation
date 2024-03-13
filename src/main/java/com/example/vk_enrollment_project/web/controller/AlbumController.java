package com.example.vk_enrollment_project.web.controller;

import com.example.vk_enrollment_project.domain.audit.DomainType;
import com.example.vk_enrollment_project.domain.audit.RequestType;
import com.example.vk_enrollment_project.service.AuditLogService;
import com.example.vk_enrollment_project.web.dto.album.AlbumDto;
import com.example.vk_enrollment_project.web.dto.photo.PhotoDto;
import com.example.vk_enrollment_project.web.feign_client.AlbumClient;
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
@RequestMapping("/api/v1/albums")
@RequiredArgsConstructor
@PreAuthorize("@customSecurityExpression.hasPostsAccessRole()")
public class AlbumController {

    private final AlbumClient albumClient;
    private final AuditLogService auditLogService;

    @GetMapping
    public List<AlbumDto> getAlbums(@AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(null, userDetails.getUsername(), RequestType.GET, DomainType.ALBUMS);
        return albumClient.getAlbums();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "AlbumClient::getAlbum", key = "#id")
    public AlbumDto getAlbum(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.GET, DomainType.ALBUMS);
        return albumClient.getAlbum(id);
    }

    @GetMapping("/{id}/photos")
    public List<PhotoDto> getPhotos(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.GET, DomainType.ALBUMS);
        return albumClient.getPhotos(id);
    }

    @PostMapping
    public AlbumDto createAlbum(@RequestBody AlbumDto album, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(album.toString(), userDetails.getUsername(), RequestType.POST, DomainType.ALBUMS);
        return albumClient.createAlbum(album);
    }

    @PutMapping("/{id}")
    @CachePut(value = "AlbumClient::getAlbum", key = "#id")
    public AlbumDto updateAlbum(@PathVariable Long id, @RequestBody AlbumDto album, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(album.toString(), userDetails.getUsername(), RequestType.UPDATE, DomainType.ALBUMS);
        return albumClient.updateAlbum(id, album);
    }

    @PatchMapping("/{id}")
    @CachePut(value = "AlbumClient::getAlbum", key = "#id")
    public AlbumDto patchAlbum(@PathVariable Long id, @RequestBody AlbumDto album, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(album.toString(), userDetails.getUsername(), RequestType.UPDATE, DomainType.ALBUMS);
        return albumClient.patchAlbum(id, album);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "AlbumClient::getAlbum", key = "#id")
    public void deleteAlbum(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        auditLogService.create(id.toString(), userDetails.getUsername(), RequestType.DELETE, DomainType.ALBUMS);
        albumClient.deleteAlbum(id);
    }
}
