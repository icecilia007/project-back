package com.brzas.project.helpers;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final String timestamp;
    private final int status;
    private final String error;
    private final String path;
    private final String message;

    public ErrorResponse(HttpStatus status, String message, String path) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.path = path;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
}
