package com.achchaimae.loisiresa.Domain.conversation.dto;

import com.achchaimae.loisiresa.Domain.message.Message;
import com.achchaimae.loisiresa.Domain.message.dto.MessageReqDTO;
import com.achchaimae.loisiresa.Domain.user.dto.UserReqDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ConversationRespDTO {
    private int id;
    private LocalDate createdDate;
    private List<UserReqDTO> users;
    List<MessageReqDTO> messages;
}
