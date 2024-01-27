package com.achchaimae.loisiresa.Domain.activity.dto;

import com.achchaimae.loisiresa.Domain.media.dto.MediaReqDTO;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityReqDTO {
    private int id;

    private List<MediaReqDTO> mediaList;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Tariff cannot be null")
    private Float tariff;

    private Integer rating;

    @NotNull(message = "Club ID cannot be null")
    private Integer club_id;
}
