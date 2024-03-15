package com.achchaimae.loisiresa.Domain.club;

import com.achchaimae.loisiresa.Domain.activity.Activity;
import com.achchaimae.loisiresa.Domain.user.contact.Contact;
import com.achchaimae.loisiresa.Domain.user.guide.Guide;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "Phone number cannot be null")
    private String  phone;
    @NotBlank(message = "logo is required")
    private String logo;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Contact> contactList;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Guide> guides;
}
