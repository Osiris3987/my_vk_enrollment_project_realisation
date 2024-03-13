package com.example.vk_enrollment_project.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AddressDto implements Serializable {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDto geo;

}
