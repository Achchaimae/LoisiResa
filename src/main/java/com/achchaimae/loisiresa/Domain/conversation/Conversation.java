package com.achchaimae.loisiresa.Domain.conversation;

import com.achchaimae.loisiresa.Domain.message.Message;
import com.achchaimae.loisiresa.Domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private LocalDate createdDate;

    @ManyToMany
    @JoinTable(
            name = "user_conversation",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> messages;


//    public void addParticipant(User user) {
//        if (this.users == null) {
//            this.users = new ArrayList<>();
//        }
//
//        if (this.users.size() < 2) {
//            this.users.add(user);
//        } else {
//            throw new IllegalStateException("A conversation can only have two users.");
//        }
//    }
}
