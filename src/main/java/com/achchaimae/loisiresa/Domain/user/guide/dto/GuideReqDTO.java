package com.achchaimae.loisiresa.Domain.user.guide.dto;

import com.achchaimae.loisiresa.security.user.dto.UserRespDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
public class GuideReqDTO extends UserRespDTO {
    private Integer id;
    private LocalDate dateOfSubscription;
    private int club_id;

}
