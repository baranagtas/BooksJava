package com.example.demo.exceptions;

public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 2;

    public BadRequestException(String message) {
        super(message);
    }
}