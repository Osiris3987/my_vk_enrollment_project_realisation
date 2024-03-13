package com.example.vk_enrollment_project.web.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class TodoDto {
    private Integer userId;
    private Integer id;
    private String title;
    private Boolean completed;
}
