// src/main/java/com/todo/todo_api/exception/ApiError.java
package com.todo.todo_api.exception;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class ApiError {
    private final int status;
    private final String error;
    private final String message;
    private final LocalDateTime timestamp;
    private final Map<String, String> validationErrors;

    public ApiError(HttpStatus status, String message, LocalDateTime timestamp) {
        this(status, message, timestamp, null);
    }

    public ApiError(HttpStatus status, String message, LocalDateTime timestamp, Map<String, String> validationErrors) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.timestamp = timestamp;
        this.validationErrors = validationErrors;
    }

    // Getters
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Map<String, String> getValidationErrors() { return validationErrors; }
}