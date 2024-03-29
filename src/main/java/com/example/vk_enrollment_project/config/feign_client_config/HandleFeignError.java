package com.example.vk_enrollment_project.config.feign_client_config;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleFeignError {
    Class<? extends FeignExceptionHandler> value();
}
