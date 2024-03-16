package ru.jucharick.TasksAPI.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}