package com.ecom.productservice.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "PRODUCT_IMPORT")
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ProductImport extends BaseEntity {
    @Column(name = "created_date", updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createDateTime;
    @Column(name = "total_imported")
    private Integer totalImported;
    @Column(name = "total_not_imported")
    private Integer totalNotImported;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @PrePersist
    private void prePersist() {
        this.createDateTime = LocalDateTime.now();
    }
}
