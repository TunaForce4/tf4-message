package com.tunaforce.message.message.service;

import com.slack.api.methods.SlackApiException;
import com.tunaforce.message.api.ApiResponse;
import com.tunaforce.message.cmmn.SlackMsg;
import com.tunaforce.message.message.dto.request.CreateMessageLogRequestDto;
import com.tunaforce.message.message.dto.response.CreateMessageLogResponseDto;
import com.tunaforce.message.message.dto.response.UserInformationResponseDto;
import com.tunaforce.message.message.entity.MasterToken;
import com.tunaforce.message.message.repository.TokenKeyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final TokenKeyJpaRepository tokenKeyJpaRepository;

    private final ClientAuthService clientAuthService;
    //슬랙 앱으로 보내는 함수 사용
    private final SlackMsg slackMsg;

    public CreateMessageLogResponseDto sendMessage(Long senderId, CreateMessageLogRequestDto createMessageLogRequestDto) throws SlackApiException, IOException {
        UserInformationResponseDto senderAppId = clientAuthService.getUserInfo(senderId);
        UserInformationResponseDto receiverAppId = clientAuthService.getUserInfo(createMessageLogRequestDto.receiverId());
        //redis에 저장로직 추후 추가

        //받은 값과 조회한 값을 기준으로 응답 데이터 만듬
        CreateMessageLogResponseDto resultData = new CreateMessageLogResponseDto(
                senderAppId.getName(),
                createMessageLogRequestDto.content(),
                receiverAppId.getName()
        );
        ApiResponse<CreateMessageLogResponseDto> resultDto = ApiResponse.success(resultData);
        //앱을 통한 메세지 송신 로직 구성

        //토큰 받아오는 로직
        //이 테이블은 여러개의 값이 존재할 수 없음
        MasterToken tempKeys = tokenKeyJpaRepository.findAll().get(0);


        slackMsg.sendDirectMessage(tempKeys.getMessageAppToken(), receiverAppId.getSlackId(), createMessageLogRequestDto.content());

        return resultData;
    }
}
