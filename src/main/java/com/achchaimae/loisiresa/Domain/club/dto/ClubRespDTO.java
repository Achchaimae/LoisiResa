package com.achchaimae.loisiresa.Domain.club.dto;

import com.achchaimae.loisiresa.Domain.activity.dto.ActivityReqDTO;
import com.achchaimae.loisiresa.Domain.user.contact.dto.ContactReqDTO;
import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideReqDTO;
import lombok.Data;

import java.util.List;


@Data
public class ClubRespDTO {
    private int id;
    private String name;
    private String location;
    private Integer phone;
    private String logo;

    private List<ActivityReqDTO> activities;
    private List<ContactReqDTO> contactList;
    private List<GuideReqDTO> guides;
}
