package com.nisum.bci.user_service_hex.infrastructure.in.controllers;

import com.nisum.bci.user_service_hex.application.create.UserCreateUseCase;
import com.nisum.bci.user_service_hex.application.find.UserFindUseCase;
import com.nisum.bci.user_service_hex.domain.model.PhoneCommand;
import com.nisum.bci.user_service_hex.domain.model.UserCommand;
import com.nisum.bci.user_service_hex.domain.model.UserQuery;

import com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.request.PhoneDto;
import com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.request.UserReqDto;
import com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.response.UserResDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserCreateUseCase createUser;

    @Mock
    private UserFindUseCase findUser;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private UserController controller;

    @Test
    void create_ShouldReturn201AndUserResDto() {
        // — Arrange —

        PhoneDto phoneDto = PhoneDto.builder()
                .number("72903241")
                .cityCode("1214")
                .countryCode("503")
                .build();

        UserReqDto reqDto = UserReqDto.builder()
                .name("Rudy Sorto")
                .email("rudy.sorto@ejemplo.com")
                .password("Admin@123")
                .phones(List.of(phoneDto))
                .build();

        PhoneCommand phoneCmd = PhoneCommand.builder()
                .phoneNumber("72903241")
                .cityCode("1214")
                .countryCode("503")
                .build();

        UserCommand cmd = UserCommand.builder()
                .name(reqDto.getName())
                .email(reqDto.getEmail())
                .password(reqDto.getPassword())
                .phones(List.of(phoneCmd))
                .build();

        UserQuery query = UserQuery.builder()
                .id(UUID.randomUUID())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("fake-jwt-token")
                .isActive(true)
                .password("IGNORED")
                .build();

        UserResDto resDto = new UserResDto();

        when(mapper.map(reqDto, UserCommand.class)).thenReturn(cmd);
        when(createUser.createUser(cmd)).thenReturn(query);
        when(mapper.map(query, UserResDto.class)).thenReturn(resDto);

        // — Act —
        ResponseEntity<UserResDto> response = controller.create(reqDto);

        // — Assert —
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        assertThat(response.getBody()).isSameAs(resDto);

        verify(mapper).map(reqDto, UserCommand.class);
        verify(createUser).createUser(cmd);
        verify(mapper).map(query, UserResDto.class);

        verifyNoMoreInteractions(mapper, createUser, findUser);
    }
}