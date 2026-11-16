package com.book.store.service.impl;

import com.book.store.dto.external.UserRegistrationRequestDto;
import com.book.store.dto.internal.UserResponseDto;
import com.book.store.exception.RegistrationException;
import com.book.store.mapper.UserMapper;
import com.book.store.model.User;
import com.book.store.repository.user.UserRepository;
import com.book.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) {
        if (userRepository.existsByEmail(userRegistrationRequestDto.email())) {
            throw new RegistrationException("User already exists. Params: email="
                    + userRegistrationRequestDto.email());
        }

        User newUser = userRepository.save(
                userMapper.toModel(userRegistrationRequestDto));
        return userMapper.toDto(newUser);
    }
}
