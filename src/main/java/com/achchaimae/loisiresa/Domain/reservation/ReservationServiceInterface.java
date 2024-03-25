package com.achchaimae.loisiresa.Domain.reservation;

import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationIDReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationRespDTO;

import java.util.List;

public interface ReservationServiceInterface {
    List<ReservationRespDTO> getReservations();
    ReservationRespDTO saveReservation(ReservationReqDTO reservationReqDTO);
    Integer Delete(ReservationIDReqDTO reservationIDReq);
    ReservationRespDTO findReservation(Integer reservationId);

    List<ReservationRespDTO> findReservationsByClubId(Integer clubId);

    ReservationRespDTO refuseReservation(Integer clientId, Integer activityId);
    ReservationRespDTO acceptReservation(Integer clientId, Integer activityId);
}
