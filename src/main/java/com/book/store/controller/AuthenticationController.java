package com.book.store.controller;

import com.book.store.dto.external.UserRegistrationRequestDto;
import com.book.store.dto.internal.UserResponseDto;
import com.book.store.exception.RegistrationException;
import com.book.store.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Manager", description = "Endpoints for managing users.")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @Operation(
            summary = "Create user"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully created new user."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Registration failed."
                            + "User with current credential is already exist."
            )
    })
    @PostMapping("/registration")
    public UserResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        return userService.register(userRegistrationRequestDto);
    }
}
