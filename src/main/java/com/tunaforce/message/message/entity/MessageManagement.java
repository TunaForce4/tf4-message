package com.tunaforce.message.message.entity;

import com.slack.api.methods.SlackApiException;
import com.tunaforce.message.message.dto.request.CreateMessageLogRequestDto;
import com.tunaforce.message.message.dto.response.MessageLogResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.UUID;


@Getter
@Entity
@Table(name = "p_messageLog")
@NoArgsConstructor
@AllArgsConstructor
public class MessageManagement extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageIdx;
    @Column(nullable = false)
    private String content;
    @Column(name = "sender_Id", updatable = false, nullable = false)
    private UUID senderId;
    @Column(name = "receiver_Id", updatable = false, nullable = false)
    private UUID receiverId;


    public MessageManagement(String content, UUID senderId, UUID receiverId) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public MessageManagement(MessageLogResponseDto  messageLogResponseDto){
        this.content = messageLogResponseDto.getContent();
        this.senderId = messageLogResponseDto.getSenderId();
        this.receiverId = messageLogResponseDto.getReceiverId();
    }

    public MessageManagement(CreateMessageLogRequestDto createMessageLogRequestDto) {
        this.content = createMessageLogRequestDto.content();
        this.senderId = createMessageLogRequestDto.senderId();
        this.receiverId = createMessageLogRequestDto.receiverId();
    }

    public void deleteMessageLog(UUID messageId) {
        delete(messageId);
    }
}


