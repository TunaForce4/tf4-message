package com.tunaforce.message.message.dto.request;

public record CreateMessageLogRequestDto(
        String content,
        //Sender의 경우 헤더에 포함된 user_id로 구분
        Long senderId,
        Long receiverId
) {

}
