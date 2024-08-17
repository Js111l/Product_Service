package com.ecom.productservice.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "PRODUCT")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    private LocalDateTime created_date;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "detail_url")
    private String detailUrl;

    @PrePersist
    void setCreatedAt() {
        this.created_date = LocalDateTime.now();
    }
}
