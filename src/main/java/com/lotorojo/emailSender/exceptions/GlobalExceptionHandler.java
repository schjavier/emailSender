package com.lotorojo.emailSender.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecipientValidationException.class)
    public ResponseEntity<String> handleRecipientValidationException(RecipientValidationException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());

    }


}
