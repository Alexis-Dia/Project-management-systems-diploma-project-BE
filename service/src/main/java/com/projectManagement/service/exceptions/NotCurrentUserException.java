package com.projectManagement.service.exceptions;

public class NotCurrentUserException extends RuntimeException {

    public NotCurrentUserException() {
    }

    public NotCurrentUserException(String message) {
        super(message);
    }

    public NotCurrentUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCurrentUserException(Throwable cause) {
        super(cause);
    }
}