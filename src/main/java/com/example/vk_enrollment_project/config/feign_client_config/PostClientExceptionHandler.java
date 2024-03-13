package com.example.vk_enrollment_project.config.feign_client_config;

import com.example.vk_enrollment_project.domain.exception.PostNotFoundException;
import feign.Response;
import org.springframework.cloud.openfeign.support.FeignUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PostClientExceptionHandler implements FeignExceptionHandler {
    @Override
    public Exception handle(Response response) {
        HttpStatus httpStatus = HttpStatus.resolve(response.status());
        String body = response.body().toString();
        assert httpStatus != null;
        if (httpStatus.is4xxClientError()) {
            return new PostNotFoundException(body);
        }
        return new RuntimeException(body);
    }
}
