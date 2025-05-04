package com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto implements Serializable {
    @NotBlank(message = "El campo number es requerido")
    @Schema(name = "number", description = "Numero de telefono", example = "72903241")
    private String number;
    @NotBlank(message = "El campo cityCode es requerido")
    @Schema(name = "cityCode", description = "Codigo de cuidad", example = "1214")
    private String cityCode;
    @NotBlank(message = "El campo countryCode es requerido")
    @Schema(name = "countryCode", description = "Codigo de pais", example = "503")
    private String countryCode;
}
