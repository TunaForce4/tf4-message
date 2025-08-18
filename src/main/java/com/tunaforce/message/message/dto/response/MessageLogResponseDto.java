package com.tunaforce.message.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class MessageLogResponseDto {
    private String senderName;
    private String content;
    private String receiverName;
    private UUID senderId;
    private UUID receiverId;

    public MessageLogResponseDto(String name, String content, String name1) {
        this.senderName = name;
        this.content = content;
        this.receiverName = name1;
    }


    public String getFormMessage(){
        return "From. " + senderName +
                " -> " + content;
    }
}
