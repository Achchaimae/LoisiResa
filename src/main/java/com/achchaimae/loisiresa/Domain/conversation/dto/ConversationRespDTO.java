package com.achchaimae.loisiresa.Domain.conversation.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConversationRespDTO {
    private int id;
    private LocalDate createdDate;
}
