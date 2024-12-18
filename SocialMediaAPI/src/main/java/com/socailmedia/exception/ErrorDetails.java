package com.socailmedia.exception;

import java.util.Date;

public class ErrorDetails {

	private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(String message, String details) {
        this.timestamp = new Date();
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
