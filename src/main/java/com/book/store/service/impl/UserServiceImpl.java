package com.book.store.service.impl;

import com.book.store.dto.external.UserRegistrationRequestDto;
import com.book.store.dto.internal.UserResponseDto;
import com.book.store.exception.RegistrationException;
import com.book.store.mapper.UserRegistrationResponseMapper;
import com.book.store.mapper.UserResponseMapper;
import com.book.store.model.User;
import com.book.store.repository.user.UserRepository;
import com.book.store.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRegistrationResponseMapper userRegistrationResponseMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) {
        Optional<User> user = userRepository.findByEmail(userRegistrationRequestDto.email());

        if (user.isPresent()) {
            throw new RegistrationException("User is already exists.");
        }

        User newUser = userRepository.save(
                userRegistrationResponseMapper.toModel(userRegistrationRequestDto));
        return userResponseMapper.toDto(newUser);
    }
}
