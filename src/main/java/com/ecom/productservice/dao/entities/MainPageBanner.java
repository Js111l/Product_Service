package com.ecom.productservice.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "MAIN_PAGE_BANNER")
@Data
@EqualsAndHashCode(callSuper = true)
public class MainPageBanner extends BaseEntity {
    @Column(name = "active")
    private Boolean active;
    @Column(name = "image_url")
    private String imageUrl;
}
