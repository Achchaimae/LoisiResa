package com.achchaimae.loisiresa.Domain.activity.dto;
import com.achchaimae.loisiresa.Domain.media.dto.MediaReqDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityReqDTO {
    private int id;
    private List<MediaReqDTO> mediaList;
    private String name;
    private Float tariff;
    private Integer rating;
    private Integer club_id;


}
