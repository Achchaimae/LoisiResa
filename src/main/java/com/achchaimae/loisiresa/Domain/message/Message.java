package com.achchaimae.loisiresa.Domain.message;


import com.achchaimae.loisiresa.Domain.conversation.Conversation;
import com.achchaimae.loisiresa.security.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String content;
    private LocalTime time;
    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
