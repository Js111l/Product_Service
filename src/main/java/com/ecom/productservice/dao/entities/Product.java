package com.ecom.productservice.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Table(name = "PRODUCT")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

    private String name;
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "start_date_of_sale")
    private LocalDate startDateOfSale;

    @Column(name = "end_date_of_sale")
    private LocalDate endDateOfSale;

    @Column(name = "image_url")
    private String imageUrl;

    @PrePersist
    void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public Product(
            String name,
            String description,
            LocalDate startDateOfSale,
            LocalDate endDateOfSale) {
        this.name = name;
        this.description = description;
        this.startDateOfSale = startDateOfSale;
        this.endDateOfSale = endDateOfSale;
    }
}
