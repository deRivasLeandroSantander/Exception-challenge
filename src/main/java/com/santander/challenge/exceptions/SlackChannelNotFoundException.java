package com.santander.challenge.exceptions;

public class SlackChannelNotFoundException extends RuntimeException {
    public SlackChannelNotFoundException(String message) { super(message); }
}
