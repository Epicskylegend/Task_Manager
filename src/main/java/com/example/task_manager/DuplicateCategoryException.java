package com.example.task_manager;

class DuplicateCategoryException extends Exception{
    public DuplicateCategoryException(String errorMessage) {
        super(errorMessage);
    }
}
