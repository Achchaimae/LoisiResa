package com.achchaimae.loisiresa.Domain.user.contact.dto;

import com.achchaimae.loisiresa.security.user.dto.UserRespDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
public class ContactReqDTO extends UserRespDTO {
    private Integer id;
    private LocalDate firstDateContact;
    private int club_id;
}
