package com.achchaimae.loisiresa.Domain.user.contact.dto;

import com.achchaimae.loisiresa.Domain.club.dto.ClubReqDTO;
import com.achchaimae.loisiresa.security.user.dto.UserRespDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactRespDTO extends UserRespDTO {
    private Integer id;
    private LocalDate firstDateContact;
    private ClubReqDTO club;
}
