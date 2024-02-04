package com.achchaimae.loisiresa.Domain.user.client.dto;

import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationReqDTO;
import com.achchaimae.loisiresa.security.user.dto.UserRespDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ClientRespDTO extends UserRespDTO {

    private LocalDate dateOfSubscription;

    private List<ReservationReqDTO> reservations;
}
