package com.nisum.bci.user_service_hex.infrastructure.components.exceptions;

public class DuplicateDataException extends RuntimeException{
    public DuplicateDataException(String message) {
        super(message);
    }
}
