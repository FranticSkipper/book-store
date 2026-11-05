package com.book.store.validation.user.password;

import com.book.store.dto.external.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NonNull;

public class PasswordsMatchValidator
        implements ConstraintValidator<PasswordsMatchValidation, UserRegistrationRequestDto> {
    @Override
    public boolean isValid(
            @NonNull UserRegistrationRequestDto userRegistrationRequestDto,
            ConstraintValidatorContext context
    ) {
        return userRegistrationRequestDto
                .password()
                .equals(userRegistrationRequestDto.repeatPassword());
    }
}
