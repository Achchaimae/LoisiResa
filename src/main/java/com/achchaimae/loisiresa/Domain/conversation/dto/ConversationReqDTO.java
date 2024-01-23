package com.achchaimae.loisiresa.Domain.conversation.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConversationReqDTO {
    private int id;
    private LocalDate createdDate;
}
