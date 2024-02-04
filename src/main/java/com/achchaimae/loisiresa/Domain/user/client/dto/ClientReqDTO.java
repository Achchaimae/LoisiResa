package com.achchaimae.loisiresa.Domain.user.client.dto;

import com.achchaimae.loisiresa.security.user.dto.UserRespDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
public class ClientReqDTO  extends UserRespDTO {

    private LocalDate dateOfSubscription;


}
