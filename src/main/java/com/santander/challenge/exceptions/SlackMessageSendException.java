package com.santander.challenge.exceptions;

public class SlackMessageSendException extends RuntimeException {
    public SlackMessageSendException(String message) { super(message); }
}
