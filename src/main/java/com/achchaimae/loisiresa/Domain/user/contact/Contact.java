package com.achchaimae.loisiresa.Domain.user.contact;

import com.achchaimae.loisiresa.Domain.club.Club;
import com.achchaimae.loisiresa.Domain.user.User;
import jakarta.persistence.*;
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
public class Contact extends User {

    @NotBlank(message = "first Date Contact required")
    private LocalDate firstDateContact;
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

}
