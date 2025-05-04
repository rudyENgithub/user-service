package com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.nisum.bci.user_service_hex.infrastructure.components.utils.constants.GeneralField.LOCAL_DATE_TIME_FORMAT_PATTERN;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResDto {
    private UUID id;
    private String token;
    private Boolean isActive;
    @DateTimeFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    @JsonFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime created;
    @DateTimeFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    @JsonFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime modified;
    @DateTimeFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    @JsonFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime lastLogin;
}
