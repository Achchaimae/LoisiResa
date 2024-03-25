package com.achchaimae.loisiresa.Domain.reservation;

import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationIDReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationRespDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    ReservationServiceInterface reservationServiceInterface;

    ModelMapper modelMapper;

    public ReservationController( ReservationServiceInterface reservationServiceInterface,ModelMapper modelMapper){
        this.reservationServiceInterface = reservationServiceInterface;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public ResponseEntity<List<ReservationRespDTO>> getReservations(){
        return ResponseEntity.ok().body(reservationServiceInterface.getReservations());
    }

@PostMapping
public ResponseEntity<ReservationRespDTO> save(@Valid @RequestBody ReservationReqDTO reservation) {
    ReservationRespDTO reservationResponse = reservationServiceInterface.saveReservation(reservation);
    return ResponseEntity.ok().body(reservationResponse);
}
    @DeleteMapping("/{activity_id}/{client_id}")
    public void delete(@PathVariable Integer activity_id,@PathVariable Integer client_id) {
        ReservationIDReqDTO reservationIdReqDTO = new ReservationIDReqDTO();
        reservationIdReqDTO.setActivity_id(activity_id);
        reservationIdReqDTO.setClient_id(client_id);
        Integer deleted = reservationServiceInterface.Delete(reservationIdReqDTO);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<ReservationRespDTO> getReservationById(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(reservationServiceInterface.findReservation(id));
    }

    @GetMapping("/find-by-club/{clubId}")
    public ResponseEntity<List<ReservationRespDTO>> getReservationsByClubId(@PathVariable Integer clubId) {
        List<ReservationRespDTO> reservations = reservationServiceInterface.findReservationsByClubId(clubId);
        return ResponseEntity.ok().body(reservations);
    }
    @PostMapping("/accept")
    public ResponseEntity<ReservationRespDTO> acceptReservation(@RequestParam Integer clientId, @RequestParam Integer activityId) {
        try {
            ReservationRespDTO acceptedReservation = reservationServiceInterface.acceptReservation(clientId, activityId);
            return ResponseEntity.ok().body(acceptedReservation);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(null);
        }
    }

    @PostMapping("/refuse")
    public ResponseEntity<ReservationRespDTO> refuseReservation(@RequestParam Integer clientId, @RequestParam Integer activityId) {
        try {
            ReservationRespDTO refusedReservation = reservationServiceInterface.refuseReservation(clientId, activityId);
            return ResponseEntity.ok().body(refusedReservation);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(null);
        }
    }
}
