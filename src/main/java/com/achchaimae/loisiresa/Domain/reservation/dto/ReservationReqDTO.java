package com.achchaimae.loisiresa.Domain.reservation.dto;

import com.achchaimae.loisiresa.Domain.reservation.enumeration.Etat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ReservationReqDTO {
    @NotNull(message = "Reservation ID is required")
    private ReservationIDReqDTO id;
    @NotNull(message = "Date is required")
    @Future(message = "Date must be in the future")
    private LocalDate date;
    @Positive(message = "Number of persons must be greater than zero")
    private  Integer nbrPerson;
    private Etat etat;
}
