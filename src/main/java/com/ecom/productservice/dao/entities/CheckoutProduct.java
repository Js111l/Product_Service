package com.ecom.productservice.dao.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CHECKOUT_PRODUCT")
@Data
@EqualsAndHashCode(callSuper = true)
public class CheckoutProduct extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "quantity")
    private Integer quantity;
}

