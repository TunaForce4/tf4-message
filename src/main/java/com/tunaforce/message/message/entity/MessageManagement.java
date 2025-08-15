package com.tunaforce.message.message.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
    @Column( name = "sender_Id",updatable = false,nullable = false)
    private Long senderId;
    @Column(name = "receiver_Id", updatable = false,nullable = false)
    private Long receiverId;


    public void MessageManagement (String content, Long senderId, Long receiverId) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}


