package com.tunaforce.message;

import com.slack.api.methods.SlackApiException;
import com.tunaforce.message.cmmn.SlackMsg;
import com.tunaforce.message.message.service.MessageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class SlackTest {


    @Test
    @DisplayName("Slack Messaging Test")
    public void SlackTest() throws SlackApiException, IOException {
        SlackMsg slackMsg = new SlackMsg();
        String userId = "U08U19Q4N8M";
        String token = "xoxb-8953330136789-9365014243201-aBZLUFfIsQkid5JXZr9a22aV";
        slackMsg.sendDirectMessage(token, userId, "Writing Test");
        //Assertions.assertThat(slackMsg.sendDirectMessage(token, userId, "Test"));
    }
}
