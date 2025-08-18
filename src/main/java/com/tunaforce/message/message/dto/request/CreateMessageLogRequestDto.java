package com.tunaforce.message.message.dto.request;

import java.util.UUID;

public record CreateMessageLogRequestDto(
        String content,
        //Sender의 경우 헤더에 포함된 user_id로 구분
        UUID senderId,
        UUID receiverId
) {

}
