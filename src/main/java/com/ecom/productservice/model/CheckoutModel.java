package com.ecom.productservice.model;

public record CheckoutModel(
        String userId,
        ProductModel product,
        long quantity
) {
    public record ProductModel(Long id){}
}