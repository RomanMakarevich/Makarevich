package com.example.ecxeption;

public class SuchUserAlreadyExistException extends Exception {
    public SuchUserAlreadyExistException() {
        super();
    }

    public SuchUserAlreadyExistException(final String message) {
        super(message);
    }
}
