package com.achchaimae.loisiresa.Domain.user.contact.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactReqDTO {
    private LocalDate firstDateContact;
    private int club_id;
}
