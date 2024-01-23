package com.achchaimae.loisiresa.Domain.club;


import com.achchaimae.loisiresa.Domain.activity.Activity;
import com.achchaimae.loisiresa.Domain.user.contact.Contact;
import com.achchaimae.loisiresa.Domain.user.guide.Guide;
import jakarta.persistence.*;
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
    private String name;
    private String location;
    private Integer phone;
    private String logo;
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Contact> contactList;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Guide> guides;
}
