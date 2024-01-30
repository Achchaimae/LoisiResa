package com.achchaimae.loisiresa.Domain.reservation;

import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationIDReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationRespDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    public ResponseEntity<ReservationRespDTO> save(@Valid @RequestBody ReservationReqDTO reservation){
        ReservationRespDTO reserrvation1 = reservationServiceInterface.saveReservation(reservation);
        return ResponseEntity.ok().body(reserrvation1);
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


}
