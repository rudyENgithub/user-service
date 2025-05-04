package com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.request;

import com.nisum.bci.user_service_hex.infrastructure.components.validator.ValidPasswordFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDto {
    @NotBlank(message = "Name is required")
    @Schema(name = "name", description = "name", example = "Jhon")
    private String name;
    @Pattern(
            regexp = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Za-z]{2,}$",
            message = "Invalid email format"
    )
    @Schema(name = "email", description = "email", example = "jhon.doe@nisum.com")
    private String email;
    @NotBlank(message = "Password is required")
    @ValidPasswordFormat
    @Schema(name = "password", description = "password", example = "Admin@123")
    private String password;
    @NotNull(message = "The phone list cannot be null")
    @Size(min = 1, message = "Must include at least one phone number")
    private List<PhoneDto> phones;
}
