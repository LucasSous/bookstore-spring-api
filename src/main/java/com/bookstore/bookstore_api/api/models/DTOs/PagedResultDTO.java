package com.bookstore.bookstore_api.api.models.DTOs;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResultDTO<T> {
    private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int pageSize;
}
