package com.book.store.mapper;

import com.book.store.config.MapperConfig;
import com.book.store.dto.external.UserRegistrationRequestDto;
import com.book.store.dto.internal.UserResponseDto;
import com.book.store.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);

    UserResponseDto toDto(User user);
}
