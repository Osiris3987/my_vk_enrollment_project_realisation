package com.example.vk_enrollment_project.config.feign_client_config;

import feign.Response;

public interface FeignExceptionHandler {
    Exception handle(Response response);
}
