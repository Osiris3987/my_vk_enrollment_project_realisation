package com.example.vk_enrollment_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class VkEnrollmentProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkEnrollmentProjectApplication.class, args);
    }

}
