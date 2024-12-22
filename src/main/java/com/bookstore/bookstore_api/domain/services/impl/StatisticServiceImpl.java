package com.bookstore.bookstore_api.domain.services.impl;

import com.bookstore.bookstore_api.api.models.DTOs.RentsStatusDTO;
import com.bookstore.bookstore_api.api.models.DTOs.TotalEntitiesDTO;
import com.bookstore.bookstore_api.domain.models.entities.RentEntity;
import com.bookstore.bookstore_api.domain.repositories.BookEntityRepository;
import com.bookstore.bookstore_api.domain.repositories.PublisherEntityRepository;
import com.bookstore.bookstore_api.domain.repositories.RentEntityRepository;
import com.bookstore.bookstore_api.domain.repositories.UserEntityRepository;
import com.bookstore.bookstore_api.domain.services.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final UserEntityRepository userRepository;
    private final BookEntityRepository bookRepository;
    private final PublisherEntityRepository publisherRepository;
    private final RentEntityRepository rentRepository;

    @Override
    public TotalEntitiesDTO getTotalEntities() {
        int totalUsers = (int) userRepository.count();
        int totalBooks = (int) bookRepository.count();
        int totalPublishers = (int) publisherRepository.count();
        int totalRents = (int) rentRepository.count();
        return TotalEntitiesDTO.builder()
                .totalUsers(totalUsers)
                .totalBooks(totalBooks)
                .totalPublishers(totalPublishers)
                .totalRents(totalRents)
                .build();
    }

    @Override
    public RentsStatusDTO getRentsStatus() {
        var rents = rentRepository.findAll();
        return buildRentsStatusDTO(rents);
    }

    private RentsStatusDTO buildRentsStatusDTO(List<RentEntity> rents) {
        int totalActive = 0;
        int totalDelayed = 0;
        int totalEnded = 0;
        LocalDate currentDate = LocalDate.now();

        for (RentEntity rent : rents) {
            totalDelayed += isDelayed(rent, currentDate) ? 1 : 0;
            totalEnded += isEnded(rent) ? 1 : 0;
            totalActive += isActive(rent, currentDate) ? 1 : 0;
        }

        return RentsStatusDTO.builder()
                .totalActive(totalActive)
                .totalDelayed(totalDelayed)
                .totalEnded(totalEnded)
                .build();
    }

    private boolean isDelayed(RentEntity rent, LocalDate currentDate) {
        return rent.getExpectedReturnDate().isBefore(currentDate);
    }

    private boolean isEnded(RentEntity rent) {
        return rent.getReturnDate() != null;
    }

    private boolean isActive(RentEntity rent, LocalDate currentDate) {
        return rent.getExpectedReturnDate().isAfter(currentDate) && rent.getReturnDate() == null;
    }

}
