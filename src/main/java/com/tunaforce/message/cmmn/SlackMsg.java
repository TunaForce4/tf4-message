package com.tunaforce.message.cmmn;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.request.conversations.ConversationsHistoryRequest;
import com.slack.api.methods.request.conversations.ConversationsOpenRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.methods.response.conversations.ConversationsOpenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
public class SlackMsg {

    //토큰은 auth 모듈에서 api로 받아 redis에 저장
//    @Value("${slack.api.token}")
//    private String slackToken;
    //userid로 변경하여 auth 모듈에서 불러오기
//    @Value("${slack.channel.id}")
//    private String channelId;



    public void sendMessageChannel(String slackToken, String channelId,String message) {
        Slack slack = Slack.getInstance();

        try {
            ChatPostMessageResponse response = slack.methods().chatPostMessage(req -> req
                    .token(slackToken)
                    .channel(channelId)
                    .text(message));
            boolean resultCheck;
            if (response.isOk()) {
                String timestamp = response.getTs();
                log.info("메시지 전송 성공: " + response.getMessage().getText());
                log.info("메세지 ID: " + timestamp);

                resultCheck = true;
            } else {
                log.error("메시지 전송 실패: " + response.getError());
                resultCheck = false;
            }
//            return new RequestSlackSend(
//                resultCheck,
//                message
//            );
        } catch (SlackApiException | IOException e) {
            e.printStackTrace();
//            return new RequestSlackSend(
//                    false,
//                    "Error : " + e.getMessage() + message
//            );
        }
    }
    public void sendDirectMessage(String slackToken,String userId, String message) throws IOException, SlackApiException {
        Slack slack = Slack.getInstance();

        // 1. 사용자와의 DM 대화방 열기
        ConversationsOpenResponse openResponse = slack.methods(slackToken).conversationsOpen(
                ConversationsOpenRequest.builder()
                        .users(Collections.singletonList(userId)) // 수정: List 형태로 전달
                        .build()
        );

        if (!openResponse.isOk()) {
            throw new RuntimeException("Error opening conversation: " + openResponse.getError());
        }
        else{
            log.info("From. {} \n message has sent : {}",userId ,message);
        }

        String channelId = openResponse.getChannel().getId();
        log.info("testing channelId : {} ", channelId);
        // 2. 메시지 전송
        ChatPostMessageResponse postResponse = slack.methods(slackToken).chatPostMessage(
                ChatPostMessageRequest.builder()
                        .channel(channelId)
                        .text(message)
                        .build()//D09AJSBRB8W
        );

        if (!postResponse.isOk()) {
            throw new RuntimeException("Error sending message: " + postResponse.getError());
        }
    }


    public void fetchHistory(String slackToken, String channelId, Integer brCount) {
        var client = Slack.getInstance().methods();
        try {
            var response = client.conversationsHistory(ConversationsHistoryRequest.builder()
                    .token(slackToken)
                    .channel(channelId)
                    .limit(brCount) // 원하는 메시지 개수 지정
                    .build());

            if (response.isOk() && response.getMessages() != null) {
                response.getMessages().forEach(msg ->
                        System.out.println("[" + msg.getTs() + "] " + msg.getText()));
            } else {
                System.out.println("Error fetching history: " + response.getError());
            }
        } catch (IOException | SlackApiException e) {
            e.printStackTrace();
        }
    }
}
