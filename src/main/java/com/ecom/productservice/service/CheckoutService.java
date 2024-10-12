package com.ecom.productservice.service;

import com.ecom.productservice.dao.mapper.CheckoutMapper;
import com.ecom.productservice.dao.mapper.ProductMapper;
import com.ecom.productservice.dao.repository.CheckoutRepository;
import com.ecom.productservice.model.CartModel;
import com.ecom.productservice.model.CartProductModel;
import com.ecom.productservice.model.CheckoutModel;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
@AllArgsConstructor
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;
    private final SecurityService securityService;

    public void add(CheckoutModel model) {
        final var checkoutProductOptional = this.checkoutRepository.findByProductIdUserId(model.product().id(), model.userId());
        if (checkoutProductOptional.isPresent()) {
            var entity = checkoutProductOptional.get();
            entity.setQuantity((int) (entity.getQuantity() + model.quantity()));
        } else {
            this.checkoutRepository.save(CheckoutMapper.INSTANCE.mapModelToEntity(model));
        }
    }

    public void setQuantity(Long productId, Long value, Long currentUserId) {
        this.checkoutRepository.setQuantity(productId, value, currentUserId);
    }

    public void getList(Long currentUserId) {
        this.checkoutRepository.findAllByUserId(currentUserId);
    }

    public Long getCheckoutCount(Long userId) {
        return this.checkoutRepository.getCheckoutCount(userId);
    }

    public CartModel getProductsFromCheckout(Long userId) {
        final var totalPrice = new AtomicReference<>(BigDecimal.ZERO);
        final var list = new ArrayList<CartProductModel>();
        this.checkoutRepository.findByUserId(userId).forEach((entity) -> {
            //entity.getPrice()
            var mockPrice = new BigDecimal("8999");
            totalPrice.set(totalPrice.get().add(mockPrice));
            list.add(ProductMapper.INSTANCE.mapEntityToCartModel(entity));
        });
        return new CartModel(
                totalPrice.get(),
                new BigDecimal("1499"),
                list
        );
    }
}





















