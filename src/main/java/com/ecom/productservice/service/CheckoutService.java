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
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
@AllArgsConstructor
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;
    private final SecurityService securityService;

    public void add(CheckoutModel model, String sessionId) {
        final var checkoutProductOptional = this.checkoutRepository.findByProductIdUserId(model.product().getId(), sessionId);
        if (checkoutProductOptional.isPresent()) {
            var entity = checkoutProductOptional.get();
            entity.setQuantity((int) (entity.getQuantity() + model.quantity()));
            entity.setSessionId(sessionId);
        } else {
            var entity = CheckoutMapper.INSTANCE.mapModelToEntity(model);
            entity.setSessionId(sessionId);
            this.checkoutRepository.save(entity);
        }
    }

    public void delete(List<Long> productIds, String sessionId) {
        try {
            this.checkoutRepository.deleteByProductIdsUserId(productIds, sessionId);
        }catch (Exception ex){
            throw new RuntimeException();
        }
    }

    public void setQuantity(Long productId, Long value, String sessionId) {
        this.checkoutRepository.setQuantity(productId, value, sessionId);
    }

    public void getList(String sessionId) {
        this.checkoutRepository.findAllByUserId(sessionId);
    }

    public long getCheckoutCount(String sessionId) {
        var result = this.checkoutRepository.getCheckoutCount(sessionId);
        return result == null ? 0 : result;
    }

    private BigDecimal getPrice(Long price){
        return BigDecimal.valueOf(price, 2);
    }
    public CartModel getProductsFromCheckout(String sessionId) {
        final var totalPrice = new AtomicReference<>(BigDecimal.ZERO);
        final var list = new ArrayList<CartProductModel>();
        this.checkoutRepository.findByUserSessionId(sessionId).forEach((entity) -> {
            totalPrice.set(getPrice(entity.getProduct().getPrice()).multiply(BigDecimal.valueOf(entity.getQuantity())));
            list.add(ProductMapper.INSTANCE.mapEntityToCartModel(entity));
        });
        return new CartModel(
                totalPrice.get(),
                new BigDecimal("1499"),//metoda dostawy i elo
                list
        );
    }
}





















