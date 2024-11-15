package com.ecom.productservice.model;

import lombok.Data;

public record CheckoutModel(
        ProductModel product,
        long quantity
) {
    @Data
    public static class ProductModel{
        private Long id;
    }
}