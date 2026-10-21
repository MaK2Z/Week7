package ru.maks.springday.exception;

public class StatusTransitionException extends RuntimeException {
    public StatusTransitionException(String message) {
        super(message);
    }
}