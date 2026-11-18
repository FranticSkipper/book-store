package com.book.store.service.impl;

import com.book.store.dto.external.UserRegistrationRequestDto;
import com.book.store.dto.internal.UserResponseDto;
import com.book.store.exception.EntityNotFoundException;
import com.book.store.exception.RegistrationException;
import com.book.store.mapper.UserMapper;
import com.book.store.model.Role;
import com.book.store.model.RoleName;
import com.book.store.model.User;
import com.book.store.repository.role.RoleRepository;
import com.book.store.repository.user.UserRepository;
import com.book.store.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) {
        if (userRepository.existsByEmail(userRegistrationRequestDto.email())) {
            throw new RegistrationException("User already exists. Params: email="
                    + userRegistrationRequestDto.email());
        }

        Role role = roleRepository
                .findByName(RoleName.USER)
                .orElseThrow(()
                        -> new EntityNotFoundException("Role is not found. Params: role="
                        + RoleName.USER));

        User user = userMapper.toModel(userRegistrationRequestDto);

        user.setPassword(passwordEncoder.encode(userRegistrationRequestDto.password()));
        user.setRoles(Set.of(role));

        User newUser = userRepository.save(user);

        return userMapper.toDto(newUser);
    }
}
