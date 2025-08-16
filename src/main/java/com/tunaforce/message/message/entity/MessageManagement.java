package com.tunaforce.message.message.entity;

import com.tunaforce.message.message.dto.request.CreateMessageLogRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long senderId;
    @Column(name = "receiver_Id", updatable = false, nullable = false)
    private Long receiverId;


    public MessageManagement(String content, Long senderId, Long receiverId) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
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


