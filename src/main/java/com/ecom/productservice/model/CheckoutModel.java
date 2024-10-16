package com.ecom.productservice.model;

import lombok.Data;

public record CheckoutModel(
        String userId,
        ProductModel product,
        long quantity
) {
    @Data
    public static class ProductModel{
        private Long id;
    }
}