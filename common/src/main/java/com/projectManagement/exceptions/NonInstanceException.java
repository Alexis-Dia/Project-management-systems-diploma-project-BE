package com.projectManagement.exceptions;

public class NonInstanceException extends GolosanswersException {

	public NonInstanceException(Class<?> clazz) {

		super("There is no instances for " + clazz.getName() + " class");
	}
}
