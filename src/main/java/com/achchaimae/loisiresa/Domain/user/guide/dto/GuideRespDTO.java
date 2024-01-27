package com.achchaimae.loisiresa.Domain.user.guide.dto;

import com.achchaimae.loisiresa.Domain.club.dto.ClubReqDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GuideRespDTO {
    private LocalDate dateOfSubscription;
    private ClubReqDTO club;

}
