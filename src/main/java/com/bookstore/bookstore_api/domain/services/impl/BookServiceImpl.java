package com.bookstore.bookstore_api.domain.services.impl;

import com.bookstore.bookstore_api.api.models.DTOs.*;
import com.bookstore.bookstore_api.domain.models.entities.BookEntity;
import com.bookstore.bookstore_api.domain.models.entities.BookStockEntity;
import com.bookstore.bookstore_api.domain.models.entities.PublisherEntity;
import com.bookstore.bookstore_api.domain.repositories.BookEntityRepository;
import com.bookstore.bookstore_api.domain.repositories.PublisherEntityRepository;
import com.bookstore.bookstore_api.domain.services.BookService;
import com.bookstore.bookstore_api.domain.validations.CreateBookValidator;
import com.bookstore.bookstore_api.domain.validations.UpdateBookValidator;
import com.bookstore.bookstore_api.shared.exceptions.AlreadyExistsException;
import com.bookstore.bookstore_api.shared.exceptions.EntityDeletionException;
import com.bookstore.bookstore_api.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookEntityRepository bookRepository;
    private final PublisherEntityRepository publisherRepository;
    private final CreateBookValidator createBookValidator;
    private final UpdateBookValidator updateBookValidator;
    @Override
    @Transactional
    public void create(CreateBookWithStockDTO body) {
        createBookValidator.validator(body);

        Optional<BookEntity> existingBook = bookRepository.findByName(body.name());

        if(existingBook.isPresent()) throw new AlreadyExistsException("Book already exists");

        PublisherEntity publisher = publisherRepository.findById(body.publisherId())
                .orElseThrow(() -> new NotFoundException("Publisher not found"));

        var book = buildBookEntity(body, publisher);
        var bookStock = buildBookStockEntity(body, book);

        book.setBookStock(bookStock);

        bookRepository.save(book);
    }

    @Override
    @Transactional
    public GetBookDTO getById(UUID id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        return buildGetBookDTO(book);
    }

    @Override
    @Transactional
    public PagedResultDTO<GetBookDTO> getAll(Integer page, Integer items) {
        Pageable pageRequest = PageRequest.of(page, items);
        Page<BookEntity> pagedResult = bookRepository.findAll(pageRequest);

        return buildPagedResponseDTO(pagedResult);
    }

    @Override
    public PagedResultDTO<GetBookDTO> getAllAvailable(Integer page, Integer items) {
        Pageable pageRequest = PageRequest.of(page, items);
        Page<BookEntity> pagedResult = bookRepository.findAllAvailable(pageRequest);

        return buildPagedResponseDTO(pagedResult);
    }

    @Override
    public List<GetBookDTO> getTop5MostRented() {
        Pageable pageRequest = PageRequest.of(0, 5);
        Page<BookEntity> books = bookRepository.findTop5MostRented(pageRequest);

        return buildGetBookDTOList(books);
    }

    @Override
    @Transactional
    public void update(UUID id, UpdateBookWithStockDTO body) {
        updateBookValidator.validator(body);

        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        if (!book.getName().equals(body.name())) {
            Optional<BookEntity> existingBook = bookRepository.findByName(body.name());
            if(existingBook.isPresent()) throw new AlreadyExistsException("Book already exists");
        }
        if(!body.publisherId().equals(book.getPublisher().getId())) {
            PublisherEntity publisher =  publisherRepository.findById(body.publisherId())
                    .orElseThrow(() -> new NotFoundException("Publisher not found"));
            book.setPublisher(publisher);
        }

        book.setName(body.name());
        book.setAuthor(body.author());
        book.setLaunchYear(body.launchYear());

        BookStockEntity bookStock = book.getBookStock();
        bookStock.setQuantityAvailable(body.quantityAvailable());

        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        try {
            bookRepository.deleteById(book.getId());
        } catch (DataIntegrityViolationException e) {
            throw new EntityDeletionException("This book cannot be deleted because it is being referenced in another entity");
        }

    }

    private BookEntity buildBookEntity(CreateBookWithStockDTO body, PublisherEntity publisher) {
        return BookEntity.builder()
                .name(body.name())
                .author(body.author())
                .launchYear(body.launchYear())
                .publisher(publisher)
                .build();
    }

    private BookStockEntity buildBookStockEntity(CreateBookWithStockDTO body, BookEntity book){
        return BookStockEntity.builder()
                .book(book)
                .quantityAvailable(body.quantityAvailable())
                .quantityRented(0)
                .build();
    }

    private GetBookDTO buildGetBookDTO(BookEntity book){
        var bookStock = buildBookStockDTO(book.getBookStock());
        return GetBookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .launchYear(book.getLaunchYear())
                .publisherId(book.getPublisher().getId())
                .bookStock(bookStock)
                .build();
    }

    private BookStockDTO buildBookStockDTO(BookStockEntity bookStock){
        return BookStockDTO.builder()
                .id(bookStock.getId())
                .bookId(bookStock.getBook().getId())
                .quantityAvailable(bookStock.getQuantityAvailable())
                .quantityRented(bookStock.getQuantityRented())
                .build();
    }

    private List<GetBookDTO> buildGetBookDTOList(Page<BookEntity> pagedResult){
        List<GetBookDTO> list = new ArrayList<>();
        for (BookEntity bookEntity : pagedResult.stream().toList()) {
            GetBookDTO getBookDTO = buildGetBookDTO(bookEntity);
            list.add(getBookDTO);
        }

        return list;
    }

    private PagedResultDTO<GetBookDTO> buildPagedResponseDTO(Page<BookEntity> page) {
        return PagedResultDTO.<GetBookDTO>builder()
                .data(buildGetBookDTOList(page))
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .pageSize(page.getSize())
                .build();
    }
}
