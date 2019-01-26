package com.crud.tasks.controller;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super("Task does not exist.");
    }
}
