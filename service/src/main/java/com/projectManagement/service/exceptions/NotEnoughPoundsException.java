package com.projectManagement.service.exceptions;

public class NotEnoughPoundsException extends RuntimeException {

    public NotEnoughPoundsException() {
    }

    public NotEnoughPoundsException(String message) {
        super(message);
    }

    public NotEnoughPoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughPoundsException(Throwable cause) {
        super(cause);
    }
}
