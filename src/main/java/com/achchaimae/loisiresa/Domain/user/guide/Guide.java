package com.achchaimae.loisiresa.Domain.user.guide;

import com.achchaimae.loisiresa.Domain.club.Club;
import com.achchaimae.loisiresa.security.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Guide extends User {

    @NotBlank(message = "date Of Subscription required")
    private LocalDate dateOfSubscription;
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}
