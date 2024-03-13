package com.example.vk_enrollment_project.config.feign_client_config;

import com.example.vk_enrollment_project.domain.exception.AlbumNotFoundException;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AlbumClientExceptionHandler implements FeignExceptionHandler {
    @Override
    public Exception handle(Response response) {
        HttpStatus httpStatus = HttpStatus.resolve(response.status());
        String body = response.body().toString();
        assert httpStatus != null;
        if (httpStatus.is4xxClientError()) {
            return new AlbumNotFoundException(body);
        }
        return new RuntimeException(body);
    }
}
