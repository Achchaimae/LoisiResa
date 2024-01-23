package com.achchaimae.loisiresa.Domain.reservation;

import com.achchaimae.loisiresa.Domain.activity.Activity;
import com.achchaimae.loisiresa.Domain.reservation.enumeration.Etat;
import com.achchaimae.loisiresa.Domain.user.client.Client;
import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class Reservation {

    @EmbeddedId
    private ReservationID id;
    private LocalDate date;
    private  Integer nbrPerson;
    private Etat etat;

    public Reservation(Activity activity, Client client) {
        this.id=new ReservationID(client, activity);
    }
    public Reservation(Activity activity, Client client,LocalDate date1, Integer nbrPerson1, Etat etat1) {
        this.id=new ReservationID(client, activity);
        this.date=date1;
        this.nbrPerson=nbrPerson1;
        this.etat= etat1;

    }

    public Reservation() {

    }
}
