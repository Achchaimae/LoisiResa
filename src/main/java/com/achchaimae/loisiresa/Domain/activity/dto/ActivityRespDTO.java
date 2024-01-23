package com.achchaimae.loisiresa.Domain.activity.dto;

import com.achchaimae.loisiresa.Domain.club.Club;
import com.achchaimae.loisiresa.Domain.media.dto.MediaReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.Reservation;
import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideReqDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRespDTO {
    private int id;
    private List<MediaReqDTO> mediaList;
    private String name;
    private Float tariff;
    private Integer rating;
    private List<GuideReqDTO> guideList;
    private List<Reservation> reservations;
    private Club club;
}
