package com.achchaimae.loisiresa.Domain.user.guide.dto;

import com.achchaimae.loisiresa.Domain.club.dto.ClubReqDTO;
import com.achchaimae.loisiresa.security.user.dto.UserRespDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GuideRespDTO extends UserRespDTO {
    private Integer id;
    private LocalDate dateOfSubscription;
    private ClubReqDTO club;

}
