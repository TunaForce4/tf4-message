package com.tunaforce.message.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageLogResponseDto {
    private String senderId;
    private String content;
    private String receiverId;


    public String getFormMessage(){
        return "From. " + senderId +
                " -> " + content;
    }
}
