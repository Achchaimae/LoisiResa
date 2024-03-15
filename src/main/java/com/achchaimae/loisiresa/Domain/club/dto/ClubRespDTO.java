package com.achchaimae.loisiresa.Domain.club.dto;

import com.achchaimae.loisiresa.Domain.activity.dto.ActivityReqDTO;
import com.achchaimae.loisiresa.Domain.club.Status;
import com.achchaimae.loisiresa.Domain.user.contact.dto.ContactReqDTO;
import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideReqDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ClubRespDTO {
    private int id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "Phone number cannot be null")
    private Integer phone;
    private String logo;
    private Status status;

    private List<ActivityReqDTO> activities;
    private List<ContactReqDTO> contactList;
    private List<GuideReqDTO> guides;
}
