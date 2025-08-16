package com.tunaforce.message.message.dto.request;

public record CreateMessageLogRequestDto(
        String content,
        Long senderId,
        Long receiverId
) {

}
