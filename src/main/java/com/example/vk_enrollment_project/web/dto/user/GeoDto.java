package com.example.vk_enrollment_project.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class GeoDto implements Serializable {
    private Double lat;
    private Double lng;
}
