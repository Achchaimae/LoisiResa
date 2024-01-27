package com.achchaimae.loisiresa.Domain.user.contact.dto;

import com.achchaimae.loisiresa.Domain.club.dto.ClubReqDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactRespDTO {
    private LocalDate firstDateContact;
    private ClubReqDTO club;
}
