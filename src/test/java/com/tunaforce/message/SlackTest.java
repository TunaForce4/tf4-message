package com.tunaforce.message;

import com.slack.api.methods.SlackApiException;
import com.tunaforce.message.cmmn.SlackMsg;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SlackTest {

    @Test
    @DisplayName("Slack Messaging Test")
    public void SlackTest() throws SlackApiException, IOException {
        SlackMsg slackMsg = new SlackMsg();
        String userId = "U08U19Q4N8M";
        String token = "xoxb-8953330136789-9365014243201-bCKjvQWu78vI76Uhgi62taDP";
        slackMsg.sendDirectMessage(token, userId, "Test");
        //Assertions.assertThat(slackMsg.sendDirectMessage(token, userId, "Test"));
    }
}
