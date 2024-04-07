package com.example.task_manager;

public class DuplicateTaskException extends Exception{
    public DuplicateTaskException(String errorMessage) {
        super(errorMessage);
    }
}
