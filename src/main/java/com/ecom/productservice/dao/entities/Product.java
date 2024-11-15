package com.ecom.productservice.dao.entities;

import com.ecom.productservice.model.ProductSize;
import com.ecom.productservice.model.enums.Fabric;
import com.ecom.productservice.model.enums.SleeveLength;
import com.ecom.productservice.model.enums.Style;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {
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

    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORY_MAP",
            joinColumns =
            @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    )
    private List<ProductCategory> productCategories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "price")
    private Long price;

    @Column(
            name = "sizes",
            columnDefinition = "text[]"
    )
    @Enumerated(EnumType.STRING)
    private List<ProductSize> sizes;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "fabric")
    private Fabric fabric;

    @Enumerated(EnumType.STRING)
    @Column(name = "sleeve_length")
    private SleeveLength sleeveLength;

    @Enumerated(EnumType.STRING)
    @Column(name = "style")
    private Style style;

    @PrePersist
    void setCreatedAt() {
        this.created_date = LocalDateTime.now();
    }
}
