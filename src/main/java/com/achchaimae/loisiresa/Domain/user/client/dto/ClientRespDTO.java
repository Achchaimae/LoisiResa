package com.achchaimae.loisiresa.Domain.user.client.dto;

import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationReqDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ClientRespDTO {

    private LocalDate dateOfSubscription;

    private List<ReservationReqDTO> reservations;
}
