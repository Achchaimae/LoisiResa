package com.achchaimae.loisiresa.security.user.dto;

import com.achchaimae.loisiresa.Domain.conversation.dto.ConversationReqDTO;
import com.achchaimae.loisiresa.Domain.message.dto.MessageReqDTO;
import com.achchaimae.loisiresa.Domain.user.enumeration.IdentityDocumentType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDTO {
    private Integer id;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address cannot be blank")
    private String address;
    @Size(min = 8, message = "Password must exceed 8  characters")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    private IdentityDocumentType identityDocumentType;
    private String identityNum;


    private List<ConversationReqDTO> conversations;


    private List<MessageReqDTO> messages;
}
