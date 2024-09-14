package com.bookstore.bookstore_api.domain.services;

import com.bookstore.bookstore_api.api.models.DTOs.LoginRequestDTO;
import com.bookstore.bookstore_api.api.models.DTOs.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO body);

}
