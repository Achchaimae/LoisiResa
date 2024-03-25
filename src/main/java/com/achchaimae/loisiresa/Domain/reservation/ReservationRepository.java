package com.achchaimae.loisiresa.Domain.reservation;

import com.achchaimae.loisiresa.Domain.activity.Activity;
import com.achchaimae.loisiresa.Domain.user.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    Optional<Reservation> findById_ActivityAndId_Client(Client client, Activity activity);


    Optional<Reservation> findById(ReservationID reservationId);
}
