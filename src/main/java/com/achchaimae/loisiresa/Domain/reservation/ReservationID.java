package com.achchaimae.loisiresa.Domain.reservation;

import com.achchaimae.loisiresa.Domain.activity.Activity;
import com.achchaimae.loisiresa.Domain.user.client.Client;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ReservationID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public ReservationID(){}

    public ReservationID(Client client, Activity activity){
        this.activity=activity;
        this.client=client;
    }
}
