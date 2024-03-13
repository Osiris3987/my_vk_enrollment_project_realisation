package com.example.vk_enrollment_project.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class CommentDto {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}
