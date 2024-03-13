package com.example.vk_enrollment_project.domain.exception;

public class AlbumNotFoundException extends ResourceNotFoundException{
    public AlbumNotFoundException(String message) {
        super(message);
    }
}
