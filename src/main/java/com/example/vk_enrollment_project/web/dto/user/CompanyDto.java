package com.example.vk_enrollment_project.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CompanyDto implements Serializable {
    private String name;
    private String catchPhrase;
    private String bs;
}
