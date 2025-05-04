package com.nisum.bci.user_service_hex.domain.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCommand {
    private String name;
    private String email;
    private String password;
    private List<PhoneCommand> phones;

}
