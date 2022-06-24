package com.santander.challenge.exceptions;

import com.santander.challenge.dtos.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler({
            FileNotFoundException.class,
            MailNotFoundException.class,
            SlackChannelNotFoundException.class,
    })
    public ResponseEntity<ResponseDTO> handleNotFoundException(RuntimeException e) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            UserAlreadyExistsException.class
    })
    public ResponseEntity<ResponseDTO> handleBadRequestException(RuntimeException e) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            IOException.class,
            UserSaveException.class,
            SecurityException.class,
            FileAlreadyOpenException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ResponseDTO> handleInternalServerError(RuntimeException e) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
        UnsupportedOperationException.class
    })
    public ResponseEntity<ResponseDTO> handleNotImplemented(RuntimeException e) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler({
            MailServerException.class,
            LegacyServerException.class,
            SlackServerException.class
    })
    public ResponseEntity<ResponseDTO> handleServiceUnavailableException(RuntimeException e) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({
            MailSendException.class,
            SlackMessageSendException.class,
            LegacyMessageSendException.class
    })
    public ResponseEntity<ResponseDTO> handleBadGatewayException(RuntimeException e) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Unespected error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseDTO> handleValidationException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ResponseDTO(e.getBindingResult().getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }
}
