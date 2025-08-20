package com.tunaforce.message.message.controller;


import com.slack.api.methods.SlackApiException;
import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.message.dto.request.CreateMessageLogRequestDto;
import com.tunaforce.message.message.dto.response.MessageLogResponseDto;
import com.tunaforce.message.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService msgService;

    //게이트 웨이에서 마스터 관리자를 제외한 모든 사용자에 대한 접근 제한(POST만 OPEN)
    @GetMapping
    public ResponseEntity<ApiResponse<List<CreateMessageLogRequestDto>>> getLogDatas(

    ) {

        List<CreateMessageLogRequestDto> tempSR = msgService.getAllLog();
        ApiResponse<List<CreateMessageLogRequestDto>> reaultDto = ApiResponse.success(tempSR);
        return ResponseEntity.ok(reaultDto);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<String>> sendMessage(
            @RequestBody CreateMessageLogRequestDto createMessageLogRequestDto,
            @RequestHeader("userId") UUID userId
    ) throws SlackApiException, IOException {

        //slackMsg 클래스 내부의 함수 호출
        String mlr = msgService.sendMessage(userId ,createMessageLogRequestDto);
        ApiResponse<String> reaultDto = ApiResponse.success(mlr);

        return  ResponseEntity.ok(reaultDto);
        //테이블에 로그를 남기는 로직 구현
    }



}
