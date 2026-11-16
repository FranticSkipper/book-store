package com.book.store.dto.external;

import com.book.store.validation.user.password.PasswordsMatchValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@PasswordsMatchValidation
public record UserRegistrationRequestDto(@NotBlank @Email String email,
                                         @NotBlank String password,
                                         @NotBlank String repeatPassword,
                                         @NotBlank String firstName,
                                         @NotBlank String lastName,
                                         String shippingAddress) {
}
