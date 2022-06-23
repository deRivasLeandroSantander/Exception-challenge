package com.santander.challenge.exceptions;

public class MailNotFoundException extends RuntimeException {
    public MailNotFoundException(String message) { super(message); }
}
