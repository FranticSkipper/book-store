package com.book.store.service;

import com.book.store.dto.external.UserRegistrationRequestDto;
import com.book.store.dto.internal.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto);
}
