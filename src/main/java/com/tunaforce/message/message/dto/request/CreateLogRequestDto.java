package com.tunaforce.message.message.dto.request;

public record CreateLogRequestDto(
        String content,
        Long senderId,
        Long receiverId
) {

}
