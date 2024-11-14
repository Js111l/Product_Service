package com.ecom.productservice.dao.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "USER_PRODUCT")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserProduct extends BaseEntity {
    @Column(name = "user_session_id")
    private String sessionId;
    @Column(name = "product_id")
    private Long productId;
}

