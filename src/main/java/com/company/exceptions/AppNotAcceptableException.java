package com.company.exceptions;

public class AppNotAcceptableException extends RuntimeException{
    public AppNotAcceptableException(String message) {
        super(message);
    }
}
