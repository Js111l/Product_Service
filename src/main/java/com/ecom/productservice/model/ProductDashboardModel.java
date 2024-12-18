package com.ecom.productservice.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductDashboardModel(
        Long id,
        String name,
        String description,
        LocalDateTime created_date,
        LocalDate startDate,
        LocalDate endDate,
        String imageUrl,
        String detailUrl,
        Long price
) {
}
