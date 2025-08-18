package com.tunaforce.message.message.service;

import com.slack.api.methods.SlackApiException;
import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.cmmn.SlackMsg;
import com.tunaforce.message.message.dto.request.CreateMessageLogRequestDto;
import com.tunaforce.message.message.dto.response.MessageLogResponseDto;
import com.tunaforce.message.message.dto.response.SearchLogResponseDto;
import com.tunaforce.message.message.dto.response.UserInformationResponseDto;
import com.tunaforce.message.message.entity.MasterToken;
import com.tunaforce.message.message.entity.MessageManagement;
import com.tunaforce.message.message.repository.MessageLogRepository;
import com.tunaforce.message.message.repository.TokenKeyJpaRepository;
import com.tunaforce.message.message.service.feignClient.ClientAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final TokenKeyJpaRepository tokenKeyJpaRepository;
    private final MessageLogRepository messageLogRepository;
    private final ClientAuthService clientAuthService;
    //슬랙 앱으로 보내는 함수 사용
    private final SlackMsg slackMsg;

    public String sendMessage(UUID senderId, CreateMessageLogRequestDto createMessageLogRequestDto) throws SlackApiException, IOException {
        UserInformationResponseDto senderAppId = clientAuthService.getUserInfo(senderId);
        UserInformationResponseDto receiverAppId = clientAuthService.getUserInfo(createMessageLogRequestDto.receiverId());
        //redis에 저장로직 추후 추가

        //받은 값과 조회한 값을 기준으로 응답 데이터 만듬
        MessageLogResponseDto resultData = new MessageLogResponseDto(
                senderAppId.getName(),
                createMessageLogRequestDto.content(),
                receiverAppId.getName()
        );
        //ApiResponse<String> resultDto = ApiResponse.success("null");
        //앱을 통한 메세지 송신 로직 구성

        //토큰 받아오는 로직
        //이 테이블은 여러개의 값이 존재할 수 없음
        MasterToken tempKeys = tokenKeyJpaRepository.findByDeletedAt().get(0);


        slackMsg.sendDirectMessage(tempKeys.getMessageAppToken(), receiverAppId.getSlackId(), resultData.getFormMessage());

        return "null";

    }

    public List<CreateMessageLogRequestDto> getAllLog(){
        List<MessageManagement> msgManagement = messageLogRepository.findAll();

        return msgManagement.stream()
                .map(entity -> new CreateMessageLogRequestDto(
                        entity.getContent(),
                        entity.getSenderId(),
                        entity.getReceiverId()
                ))
                .toList();
    }
}
