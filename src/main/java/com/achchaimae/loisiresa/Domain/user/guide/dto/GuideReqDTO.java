package com.achchaimae.loisiresa.Domain.user.guide.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class GuideReqDTO {
    private LocalDate dateOfSubscription;
    private int club_id;

}
