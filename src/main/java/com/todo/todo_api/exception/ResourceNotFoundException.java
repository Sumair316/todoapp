// src/main/java/com/todo/todo_api/exception/ResourceNotFoundException.java
package com.todo.todo_api.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}