package com.example.vk_enrollment_project.config.feign_client_config;

import com.example.vk_enrollment_project.domain.exception.ResourceNotFoundException;
import com.example.vk_enrollment_project.domain.exception.UserNotFoundException;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserClientExceptionHandler implements FeignExceptionHandler {
    @Override
    public Exception handle(Response response) {
        HttpStatus httpStatus = HttpStatus.resolve(response.status());
        String body = response.body().toString();
        if (httpStatus.is4xxClientError()) {
            return new UserNotFoundException(body);
        }
        return new RuntimeException(body);
    }
}
