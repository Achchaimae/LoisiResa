package com.achchaimae.loisiresa.Domain.activity;

import com.achchaimae.loisiresa.Domain.club.Club;
import com.achchaimae.loisiresa.Domain.media.Media;
import com.achchaimae.loisiresa.Domain.reservation.Reservation;
import com.achchaimae.loisiresa.Domain.user.guide.Guide;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private List<Media> mediaList;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Tariff cannot be null")
    private Float tariff;

    private Integer rating;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private List<Guide> guideList;

    @OneToMany(mappedBy = "id.activity", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}
