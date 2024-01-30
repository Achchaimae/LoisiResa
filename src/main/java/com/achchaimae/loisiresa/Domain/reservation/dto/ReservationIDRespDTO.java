package com.achchaimae.loisiresa.Domain.reservation.dto;

import com.achchaimae.loisiresa.Domain.activity.dto.ActivityRespDTO;
import com.achchaimae.loisiresa.Domain.user.client.dto.ClientRespDTO;
import lombok.Data;

@Data
public class ReservationIDRespDTO {
    private ClientRespDTO client;
    private ActivityRespDTO activity;
}
