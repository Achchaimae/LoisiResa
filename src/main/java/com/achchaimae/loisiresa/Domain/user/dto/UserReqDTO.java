package com.achchaimae.loisiresa.Domain.user.dto;

import com.achchaimae.loisiresa.Domain.conversation.Conversation;
import com.achchaimae.loisiresa.Domain.conversation.dto.ConversationReqDTO;
import com.achchaimae.loisiresa.Domain.message.Message;
import com.achchaimae.loisiresa.Domain.message.dto.MessageReqDTO;
import com.achchaimae.loisiresa.Domain.user.enumeration.IdentityDocumentType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDTO {

    private long id;

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
    private IdentityDocumentType identityDocumentType;
    private String identityNum;


    private Integer conversation_Id;


    private Integer message_Id;
}
