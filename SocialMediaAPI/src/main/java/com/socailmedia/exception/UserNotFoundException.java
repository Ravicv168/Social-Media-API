package com.socailmedia.exception;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String field,String value) {
        super(String.format("User not found with '%s' : '%s'", field,value));
    }
}
