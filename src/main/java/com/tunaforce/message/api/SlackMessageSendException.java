package com.tunaforce.message.api;

import com.slack.api.methods.SlackApiException;

public class SlackMessageSendException extends RuntimeException {
    public SlackMessageSendException(String message) {
        super(message);
    }
}
