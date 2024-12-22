package com.bookstore.bookstore_api.domain.services;

import com.bookstore.bookstore_api.api.models.DTOs.RentsStatusDTO;
import com.bookstore.bookstore_api.api.models.DTOs.TotalEntitiesDTO;

public interface StatisticService {
    TotalEntitiesDTO getTotalEntities();

    RentsStatusDTO getRentsStatus();
}
