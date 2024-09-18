package com.bookstore.bookstore_api.domain.services.impl;

import com.bookstore.bookstore_api.api.models.DTOs.LoginRequestDTO;
import com.bookstore.bookstore_api.api.models.DTOs.LoginResponseDTO;
import com.bookstore.bookstore_api.domain.models.entities.UserEntity;
import com.bookstore.bookstore_api.domain.repositories.UserEntityRepository;
import com.bookstore.bookstore_api.domain.services.AuthService;
import com.bookstore.bookstore_api.infra.security.TokenService;
import com.bookstore.bookstore_api.shared.exceptions.NotFoundException;
import com.bookstore.bookstore_api.shared.exceptions.UnauthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    @Override
    public LoginResponseDTO login(LoginRequestDTO body) {
        UserEntity user = userEntityRepository.findByEmail(body.email()).orElseThrow(() -> new NotFoundException("User not found"));
        var isCorrectPassword = passwordEncoder.matches(body.password(), user.getPassword());
        if(!isCorrectPassword) throw new UnauthorizedException("Incorrect password");

        String token = tokenService.generateToken(user);
        return LoginResponseDTO.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .token(token)
                .role(user.getRole())
                .build();
    }
}
