package com.projectManagement.exceptions;

public class GolosanswersException extends RuntimeException {

	public GolosanswersException() {

	}

	public GolosanswersException(String message) {

		super(message);
	}

	public GolosanswersException(String message, Throwable cause) {

		super(message, cause);
	}

	public GolosanswersException(Throwable cause) {

		super(cause);
	}
}
