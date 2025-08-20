package com.tunaforce.message.message.service;

import com.slack.api.methods.SlackApiException;
import com.tunaforce.message.cmmn.SlackMsg;
import com.tunaforce.message.message.dto.request.CreateMessageLogRequestDto;
import com.tunaforce.message.message.dto.response.MessageLogResponseDto;
import com.tunaforce.message.message.dto.response.auth.UserInformationResponseDto;
import com.tunaforce.message.token.entity.MasterToken;
import com.tunaforce.message.message.entity.MessageManagement;
import com.tunaforce.message.message.repository.MessageLogRepository;
import com.tunaforce.message.token.repository.TokenKeyJpaRepository;
import com.tunaforce.message.message.service.feignClient.ClientAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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
        //원래는 senderId와 receiverId를 Auth에서 name을 받아서 입력하도록 함
        //테스트로 주석 처리
        //senderID는 나중에 messageManagement에서 값을 반환 받을 때 필요
        //여기서 senderId는 유저아이디임 slackID가 아님
        UserInformationResponseDto senderAppId = clientAuthService.getUserInfo(senderId);
        UserInformationResponseDto receiverAppId = clientAuthService.getUserInfo(createMessageLogRequestDto.receiverId());

        //redis에 저장로직 추후 추가

        //받은 값과 조회한 값을 기준으로 응답 데이터 만듬
        MessageLogResponseDto resultData = new MessageLogResponseDto(
                senderAppId.getName(),
                createMessageLogRequestDto.content(),
                receiverAppId.getName(),
                senderId,
                createMessageLogRequestDto.receiverId()
        );

        //토큰 받아오는 로직
        //이 테이블은 논리적으로 제거가 안된 값이 여러개의 값이 존재할 수 없음
        //deletedAt 부분인 null이 아닌 값 즉 논리적 제거가 되지 않은 값
        MasterToken tempKeys = tokenKeyJpaRepository.findByDeletedAt().get(0);

        slackMsg.sendDirectMessage(
                tempKeys.getMessageAppToken(),
                receiverAppId.getSlackId(),
                resultData.getFormMessage()
        );

    //로그는 송신이 된 후에 쌓여야 함
        MessageManagement messageManagement = new MessageManagement(resultData);
        messageLogRepository.save(messageManagement);
        //앱을 통한 메세지 송신 로직 구성



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
