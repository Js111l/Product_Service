package com.ecom.productservice.dao.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "USER_PRODUCT")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserProduct extends BaseEntity {
    @Column(name = "user_id")
    private Long userId;

    @ManyToMany
    @JoinTable(
            name = "USER_PRODUCT_MAPPING",
            joinColumns = @JoinColumn(name = "user_product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products=new ArrayList<>();
}

