package com.achchaimae.loisiresa.Domain.conversation.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ConversationReqDTO {
    private int id;
    private LocalDate createdDate;
    private Integer user_Id;
    private Integer message_Id;
}
