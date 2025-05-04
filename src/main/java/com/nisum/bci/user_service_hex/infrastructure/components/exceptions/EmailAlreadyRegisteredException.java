package com.nisum.bci.user_service_hex.infrastructure.components.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException(String msg) { super(msg); }
}
