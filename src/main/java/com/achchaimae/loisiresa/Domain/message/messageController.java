package com.achchaimae.loisiresa.Domain.message;

import com.achchaimae.loisiresa.Domain.conversation.Conversation;
import com.achchaimae.loisiresa.Domain.conversation.ConversationRepository;
import com.achchaimae.loisiresa.security.user.User;
import com.achchaimae.loisiresa.security.user.UserRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;

@Controller
public class messageController {
//    private final MessageRepository messageRepository;
//    private final ConversationRepository conversationRepository;
//    private final UserRepository userRepository;
//
//
//
//    public messageController(MessageRepository messageRepository, ConversationRepository conversationRepository, UserRepository userRepository) {
//        this.messageRepository = messageRepository;
//        this.conversationRepository = conversationRepository;
//        this.userRepository = userRepository;
//    }
//
//    @MessageMapping("/chat/{conversationId}")
//    @SendTo("/topic/messages/{conversationId}")
//    public Message handleMessage(Message message, @DestinationVariable int conversationId) {
//        // Get the conversation and user from the message
//        Conversation conversation = conversationRepository.findById(conversationId)
//                .orElseThrow(() -> new RuntimeException("Conversation not found"));
//        User user = userRepository.findById(message.getUser().getId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Set the conversation and user on the message
//        message.setConversation(conversation);
//        message.setUser(user);
//        message.setTime(LocalTime.now());
//
//        // Save the message
//        Message savedMessage = messageRepository.save(message);
//
//        return savedMessage;
//    }
}
