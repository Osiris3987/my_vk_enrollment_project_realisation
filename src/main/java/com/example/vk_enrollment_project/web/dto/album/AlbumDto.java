package com.example.vk_enrollment_project.web.dto.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AlbumDto implements Serializable {
    private Integer userId;
    private Integer id;
    private String title;
}
