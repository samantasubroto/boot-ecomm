package com.example.springboot_demo.exceptions;

public class InvalidUserEmailException extends RuntimeException {

    public InvalidUserEmailException() {
        super();
    }

    public InvalidUserEmailException(String message) {
        super(message);
    }
}
