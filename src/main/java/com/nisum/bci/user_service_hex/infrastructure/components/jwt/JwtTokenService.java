package com.nisum.bci.user_service_hex.infrastructure.components.jwt;

import java.util.UUID;

public interface JwtTokenService {
    String generateToken(UUID userId);
    boolean validateToken(String token);
    String getUserIdFromJWT(String token);
}
