package com.nisum.bci.user_service_hex.infrastructure.in.controllers;


import com.nisum.bci.user_service_hex.application.create.UserCreateUseCase;
import com.nisum.bci.user_service_hex.application.find.UserFindUseCase;
import com.nisum.bci.user_service_hex.domain.model.UserCommand;
import com.nisum.bci.user_service_hex.domain.model.UserQuery;
import com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.base.ErrorResponseDto;
import com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.request.UserReqDto;
import com.nisum.bci.user_service_hex.infrastructure.in.controllers.dto.response.UserResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.nisum.bci.user_service_hex.infrastructure.components.utils.constants.APIField.USER_API;
import static com.nisum.bci.user_service_hex.infrastructure.components.utils.constants.GeneralField.*;

@RestController
@RequestMapping(path=USER_API, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserCreateUseCase createUser;
    private final UserFindUseCase findUser;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final ModelMapper mapper;

    @Operation(summary = USER_SUMMARY_OPERARION,
            description = USER_SUMMARY_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = USER_SUCCESSFULL,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResDto.class))),
            @ApiResponse(responseCode = "400", description = NO_USER_CREATED,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "409", description = USER_EXISTS,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResDto> create(@Valid @RequestBody UserReqDto req) {
        log.info("UserController.create {}",req.getEmail());
        UserCommand userToCreate = mapper.map(req, UserCommand.class);
        UserQuery userCreated = createUser.createUser(userToCreate);
        return new ResponseEntity<>(mapper.map(userCreated, UserResDto.class), HttpStatus.CREATED);
    }


    @Operation(
            summary = GET_USER_SUMMARY_OPERARION,
            description = GET_USER_DESC_OPERARION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = GET_USER_SUCC,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResDto.class))),
            @ApiResponse(responseCode = "401", description = GET_USER_NO_AUTH, content =  @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = GET_USER_NOTFOUND, content =  @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResDto> getUserById(
            @Parameter(
                    in = ParameterIn.PATH,
                    description = GET_USER_UUID,
                    required = true,
                    schema = @Schema(type = "string", format = "uuid")
            )
            @PathVariable UUID id) {
        log.info("UserController.getUserById {}",id);
        UserQuery userSearched = findUser.findById(id);
        return ResponseEntity.ok(mapper.map(userSearched, UserResDto.class));
    }



}