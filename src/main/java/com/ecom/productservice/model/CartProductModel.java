package com.ecom.productservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartProductModel {
    private Long id;
    private Integer quantity;
    private ProductModel product;

    @Data
    public static class ProductModel {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private String imageUrl;
    }
}
