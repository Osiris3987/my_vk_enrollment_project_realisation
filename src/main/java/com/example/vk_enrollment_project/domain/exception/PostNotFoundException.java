package com.example.vk_enrollment_project.domain.exception;

public class PostNotFoundException extends ResourceNotFoundException{

    public PostNotFoundException(String message) {
        super(message);
    }
}
