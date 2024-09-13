package com.bookstore.bookstore_api.api.controllers;

import com.bookstore.bookstore_api.api.models.DTOs.LoginRequestDTO;
import com.bookstore.bookstore_api.api.models.DTOs.CreateUserDTO;
import com.bookstore.bookstore_api.api.models.DTOs.LoginResponseDTO;
import com.bookstore.bookstore_api.domain.models.entities.UserEntity;
import com.bookstore.bookstore_api.domain.repositories.UserEntityRepository;
import com.bookstore.bookstore_api.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        UserEntity user = this.userEntityRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            var response = LoginResponseDTO.builder()
                    .userId(user.getId())
                    .userName(user.getUserName())
                    .token(token)
                    .role(user.getRole())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CreateUserDTO body){
        Optional<UserEntity> user = this.userEntityRepository.findByEmail(body.getEmail());

        if(user.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setPassword(passwordEncoder.encode(body.getPassword()));
            newUser.setEmail(body.getEmail());
            newUser.setUserName(body.getUserName());
            newUser.setRole(body.getRole());
            this.userEntityRepository.save(newUser);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

}
