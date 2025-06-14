package com.lotorojo.emailSender.exceptions;

public class RecipientValidationException extends RuntimeException {
    public RecipientValidationException(String msg) {
        super(msg);
    }
}
