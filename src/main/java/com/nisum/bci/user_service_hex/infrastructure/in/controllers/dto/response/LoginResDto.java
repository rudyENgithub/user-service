package com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResDto(
        @Schema(name = "token",description = "JWT token",example = "eyJzdWIiOiI1ZDA3YjYyMi1iY2FkLTQ5NDAtOTM4My1hYWRhZWU2MTg2MTMiLCJpYXQiOjE3NDYzOTI5MTUsImV4cCI6MTc0NjQ5MjkwNX0.fW77yuLRIE40kC15R-6y8MX0vWtR1_RvDRewhHRZGPw")
        String token
){}
