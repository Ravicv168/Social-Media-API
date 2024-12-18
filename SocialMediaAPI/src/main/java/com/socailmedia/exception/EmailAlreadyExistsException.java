package com.socailmedia.exception;

public class EmailAlreadyExistsException extends RuntimeException{

	public EmailAlreadyExistsException(String email) {
        super(String.format("Email '%s' is already in use", email));
    }
}
