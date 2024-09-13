package com.bookstore.bookstore_api.domain.services.impl;

import com.bookstore.bookstore_api.api.models.DTOs.CreateUserDTO;
import com.bookstore.bookstore_api.domain.models.entities.UserEntity;
import com.bookstore.bookstore_api.domain.repositories.UserEntityRepository;
import com.bookstore.bookstore_api.domain.services.UserService;
import com.bookstore.bookstore_api.domain.validations.CreateUserValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final CreateUserValidator createUserValidator;

    @Transactional
    @Override
    public void create(CreateUserDTO body) {
        createUserValidator.validate(body);
        Optional<UserEntity> existingUser = userEntityRepository.findByEmail(body.getEmail());

        if(existingUser.isPresent()) throw new IllegalArgumentException("User already exists.");

        var newUser = buildUserEntity(body);
        userEntityRepository.save(newUser);
    }

    private UserEntity buildUserEntity(CreateUserDTO dto) {
        return UserEntity.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();
    }
}
