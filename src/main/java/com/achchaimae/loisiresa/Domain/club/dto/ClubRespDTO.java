package com.achchaimae.loisiresa.Domain.club.dto;

import com.achchaimae.loisiresa.Domain.activity.dto.ActivityReqDTO;
import com.achchaimae.loisiresa.Domain.user.contact.dto.ContactReqDTO;
import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideReqDTO;
import lombok.Data;


@Data
public class ClubRespDTO {
    private int id;
    private String name;
    private String location;
    private Integer phone;
    private String logo;

    private ActivityReqDTO activities;
    private ContactReqDTO contactList;
    private GuideReqDTO guides;
}
