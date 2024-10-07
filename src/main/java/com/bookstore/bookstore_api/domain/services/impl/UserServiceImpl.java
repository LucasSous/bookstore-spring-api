package com.bookstore.bookstore_api.domain.services.impl;

import com.bookstore.bookstore_api.api.models.DTOs.CreateUserDTO;
import com.bookstore.bookstore_api.api.models.DTOs.GetUserDTO;
import com.bookstore.bookstore_api.api.models.DTOs.PagedResultDTO;
import com.bookstore.bookstore_api.api.models.DTOs.UpdateUserDTO;
import com.bookstore.bookstore_api.domain.models.entities.UserEntity;
import com.bookstore.bookstore_api.shared.exceptions.EntityDeletionException;
import com.bookstore.bookstore_api.shared.models.enums.RoleType;
import com.bookstore.bookstore_api.domain.repositories.UserEntityRepository;
import com.bookstore.bookstore_api.domain.services.UserService;
import com.bookstore.bookstore_api.domain.validations.CreateUserValidator;
import com.bookstore.bookstore_api.domain.validations.UpdateUserValidator;
import com.bookstore.bookstore_api.infra.security.TokenService;
import com.bookstore.bookstore_api.shared.exceptions.AlreadyExistsException;
import com.bookstore.bookstore_api.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final CreateUserValidator createUserValidator;
    private final UpdateUserValidator updateUserValidator;

    @Transactional
    @Override
    public void create(CreateUserDTO body) {
        createUserValidator.validate(body);
        Optional<UserEntity> existingUser = userEntityRepository.findByEmail(body.getEmail());

        if(existingUser.isPresent()) throw new AlreadyExistsException("User already exists");

        var newUser = buildUserEntity(body);
        userEntityRepository.save(newUser);
    }

    @Override
    public GetUserDTO getById(String token, UUID id) {
        if (!isAuthorized(token, id)) throw new AccessDeniedException("This user does not have permission for this action");
        UserEntity user = userEntityRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        return buildGetUserDTO(user);
    }

    @Override
    public PagedResultDTO<GetUserDTO> getAll(Integer page, Integer items) {
        Pageable pageRequest = PageRequest.of(page, items);
        Page<UserEntity> pagedResult = userEntityRepository.findAll(pageRequest);

        return buildPagedResponseDTO(pagedResult);
    }

    @Transactional
    @Override
    public void update(String token, UUID id, UpdateUserDTO body) {
        updateUserValidator.validate(body);
        if (!isAuthorized(token, id)) throw new AccessDeniedException("This user does not have permission for this action");
        userEntityRepository.findById(id).map(user -> {
            user.setUserName(body.getUserName());
            user.setEmail(body.getEmail());
            user.setRole(body.getRole());
            return userEntityRepository.save(user);
        })
                .orElseThrow(() -> new NotFoundException("User not found"));

    }

    @Transactional
    @Override
    public void delete(String token, UUID id) {
        UserEntity user = userEntityRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        if (!isAuthorized(token, id)) throw new AccessDeniedException("This user does not have permission for this action");

        try {
            userEntityRepository.deleteById(user.getId());
        } catch (DataIntegrityViolationException e) {
            throw new EntityDeletionException("This user cannot be deleted because it is being referenced in another entity");
        }
    }

    private UserEntity buildUserEntity(CreateUserDTO dto) {
        return UserEntity.builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();
    }

    private GetUserDTO buildGetUserDTO(UserEntity entity) {
        return GetUserDTO.builder()
                .userId(entity.getId())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }

    private List<GetUserDTO> buildGetUserDTOList(Page<UserEntity> pagedResult){
        List<GetUserDTO> list = new ArrayList<>();
        for (UserEntity userEntity : pagedResult.stream().toList()) {
            GetUserDTO getUserDTO = buildGetUserDTO(userEntity);
            list.add(getUserDTO);
        }

        return list;
    }

    private PagedResultDTO<GetUserDTO> buildPagedResponseDTO(Page<UserEntity> page) {
        return PagedResultDTO.<GetUserDTO>builder()
                .data(buildGetUserDTOList(page))
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .pageSize(page.getSize())
                .build();
    }

    private boolean isAuthorized(String token, UUID userId) {
        var loginId = tokenService.getClaimFromToken(token, "userId");
        var loginRole = tokenService.getClaimFromToken(token, "role");
        return loginId.equals(userId.toString()) || loginRole.equals(RoleType.ADMIN.name());
    }
}
