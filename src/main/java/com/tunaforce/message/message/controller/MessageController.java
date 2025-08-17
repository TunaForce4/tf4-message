package com.tunaforce.message.message.controller;


import com.slack.api.methods.SlackApiException;
import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.message.dto.request.CreateMessageLogRequestDto;
import com.tunaforce.message.message.dto.response.CreateMessageLogResponseDto;
import com.tunaforce.message.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    @PostMapping
    public ResponseEntity<ApiResponse<CreateMessageLogResponseDto>> sendMessage (
            @RequestBody CreateMessageLogRequestDto createMessageLogRequestDto,
            @RequestHeader("userId")  Long senderId
    ) throws SlackApiException, IOException {
            ApiResponse<CreateMessageLogResponseDto> resultDto =
                    ApiResponse.success(messageService.sendMessage(senderId, createMessageLogRequestDto));
        //헤더 값과 파라미터 값의 각각의 user_id를 받아서 feignClient로 auth에서 사용자 정보를 반환
        //senderId와 createMessageLogRequestDto.receiverId를 각각 페인클라이언트에서 정보값을 반환
        return ResponseEntity.ok(resultDto);
    }
}
