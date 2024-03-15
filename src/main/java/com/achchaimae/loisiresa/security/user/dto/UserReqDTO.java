package com.achchaimae.loisiresa.security.user.dto;

import com.achchaimae.loisiresa.Domain.user.enumeration.IdentityDocumentType;


import com.achchaimae.loisiresa.security.user.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDTO {

    private Integer id;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 8, message = "Password must exceed 8  characters")
    private String password;
    private Role role;

    private IdentityDocumentType identityDocumentType;
    private String identityNum;


//    private Integer conversation_Id;
//
//
//    private Integer message_Id;
}
