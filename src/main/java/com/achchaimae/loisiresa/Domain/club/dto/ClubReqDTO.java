package com.achchaimae.loisiresa.Domain.club.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ClubReqDTO {

    @NotNull(message = "id is required")
    private int id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "location is required")
    private String location;
    @NotBlank(message = "phone is required")
    private Integer phone;
    @NotBlank(message = "logo is required")
    private String logo;

}