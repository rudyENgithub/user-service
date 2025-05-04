package com.nisum.bci.user_service_hex.infrastructure.components.exceptions;

import org.springframework.security.core.AuthenticationException;
public class CustomAuthenticationException extends AuthenticationException  {
    public CustomAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
