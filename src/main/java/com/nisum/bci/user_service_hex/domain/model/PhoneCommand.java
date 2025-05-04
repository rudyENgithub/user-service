package com.nisum.bci.user_service_hex.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneCommand {
    private String phoneNumber;

    private String cityCode;

    private String countryCode;
}
