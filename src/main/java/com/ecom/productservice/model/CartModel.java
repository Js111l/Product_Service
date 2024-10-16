package com.ecom.productservice.model;

import com.ecom.productservice.model.CartProductModel;

import java.math.BigDecimal;
import java.util.List;

public record CartModel(BigDecimal totalPrice, BigDecimal shippingCost, List<CartProductModel> products) {
}
