package com.bookstore.bookstore_api.domain.services.impl;

import com.bookstore.bookstore_api.api.models.DTOs.*;
import com.bookstore.bookstore_api.domain.models.entities.BookEntity;
import com.bookstore.bookstore_api.domain.models.entities.RentEntity;
import com.bookstore.bookstore_api.domain.models.entities.UserEntity;
import com.bookstore.bookstore_api.domain.repositories.BookEntityRepository;
import com.bookstore.bookstore_api.domain.repositories.RentEntityRepository;
import com.bookstore.bookstore_api.domain.repositories.UserEntityRepository;
import com.bookstore.bookstore_api.domain.services.RentService;
import com.bookstore.bookstore_api.domain.validations.CreateRentValidator;
import com.bookstore.bookstore_api.domain.validations.UpdateRentValidator;
import com.bookstore.bookstore_api.shared.exceptions.NotFoundException;
import com.bookstore.bookstore_api.shared.models.enums.RentStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RentServiceImpl implements RentService {

    private final RentEntityRepository rentRepository;
    private final BookEntityRepository bookRepository;
    private final UserEntityRepository userRepository;
    private final CreateRentValidator createRentValidator;
    private final UpdateRentValidator updateRentValidator;

    @Override
    @Transactional
    public void create(CreateRentDTO body) {
        BookEntity book = getBookEntity(body.bookId());
        createRentValidator.validate(body, book);
        book.getBookStock().changeValuesAfterRent();
        UserEntity user = getUserEntity(body.userId());
        RentEntity rent = buildRentEntity(body, user, book);

        rentRepository.save(rent);
    }

    @Override
    @Transactional
    public GetRentDTO getById(UUID id) {
        RentEntity rent = rentRepository.findById(id).orElseThrow(() -> new NotFoundException("Rent not found"));
        return buildGetRentDTO(rent);
    }

    @Override
    @Transactional
    public PagedResultDTO<GetRentDTO> getAll(Integer page, Integer items) {
        Pageable pageRequest = PageRequest.of(page, items);
        Page<RentEntity> pagedResult = rentRepository.findAll(pageRequest);

        return buildPagedResponseDTO(pagedResult);
    }

    @Override
    public PagedResultDTO<GetRentDTO> getUserRents(Integer page, Integer items, UUID userId) {
        UserEntity user = getUserEntity(userId);
        Pageable pageRequest = PageRequest.of(page, items);
        Page<RentEntity> pagedResult = rentRepository.findByUserId(user.getId(), pageRequest);

        return buildPagedResponseDTO(pagedResult);
    }

    @Override
    @Transactional
    public void update(UUID id, UpdateRentDTO body) {
        RentEntity rent = getRentEntity(id);
        updateRentValidator.validate(body, rent);
        UserEntity user = getUserEntity(body.userId());
        BookEntity book = getBookEntity(body.bookId());

        rent.setUser(user);
        rent.setBook(book);
        rent.setExpectedReturnDate(body.expectedReturnDate());

        rentRepository.save(rent);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        RentEntity rent = getRentEntity(id);
        rentRepository.deleteById(rent.getId());
    }

    @Override
    @Transactional
    public void finalizeRent(UUID id) {
        LocalDate returnDate = LocalDate.now();
        RentEntity rent = getRentEntity(id);
        rent.setReturnDate(returnDate);

        rent.getBook().getBookStock().changeValuesAfterFinalizingRent();

        rentRepository.save(rent);
    }

    private RentEntity getRentEntity(UUID id){
        return rentRepository.findById(id).orElseThrow(() -> new NotFoundException("Rent not found"));
    }

    private UserEntity getUserEntity(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    private BookEntity getBookEntity(UUID id){
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    private RentEntity buildRentEntity(CreateRentDTO body, UserEntity user, BookEntity book){
        return RentEntity.builder()
                .user(user)
                .book(book)
                .expectedReturnDate(body.expectedReturnDate())
                .build();
    }

    private GetRentDTO buildGetRentDTO(RentEntity entity){
        return GetRentDTO.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .userName(entity.getUser().getUserName())
                .bookId(entity.getBook().getId())
                .bookName(entity.getBook().getName())
                .rentalDate(entity.getCreatedAt().toLocalDate())
                .expectedReturnDate(entity.getExpectedReturnDate())
                .returnDate(entity.getReturnDate())
                .rentStatus(getRentStatus(entity.getExpectedReturnDate(), entity.getReturnDate()))
                .build();
    }

    private RentStatus getRentStatus(LocalDate expectedReturnDate, LocalDate returnDate){
        LocalDate currentDate = LocalDate.now();
        if(expectedReturnDate.isBefore(currentDate)) return RentStatus.DELAYED;
        if(returnDate != null) return RentStatus.ENDED;
        return RentStatus.ACTIVE;
    }

    private List<GetRentDTO> buildGetRentDTOList(Page<RentEntity> pagedResult){
        List<GetRentDTO> list = new ArrayList<>();
        for (RentEntity rentEntity : pagedResult.stream().toList()) {
            GetRentDTO getRentDTO = buildGetRentDTO(rentEntity);
            list.add(getRentDTO);
        }

        return list;
    }

    private PagedResultDTO<GetRentDTO> buildPagedResponseDTO(Page<RentEntity> page){
        return PagedResultDTO.<GetRentDTO>builder()
                .data(buildGetRentDTOList(page))
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .pageSize(page.getSize())
                .build();
    }
}
