package com.example.vk_enrollment_project.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PostDto implements Serializable {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
