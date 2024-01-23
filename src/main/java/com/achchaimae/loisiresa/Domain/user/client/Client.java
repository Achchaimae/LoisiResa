package com.achchaimae.loisiresa.Domain.user.client;

import com.achchaimae.loisiresa.Domain.reservation.Reservation;
import com.achchaimae.loisiresa.Domain.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Client extends User {

    @NotBlank(message = "date Of Subscription required")
    private LocalDate dateOfSubscription;
    @OneToMany(mappedBy = "id.client",cascade = { CascadeType.MERGE, CascadeType.REMOVE })
    private List<Reservation> reservations;
}
