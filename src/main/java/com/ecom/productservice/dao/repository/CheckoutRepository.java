package com.ecom.productservice.dao.repository;

import com.ecom.productservice.dao.entities.CheckoutProduct;
import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.model.ProductModel;
import org.hibernate.annotations.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutProduct, Long> {
    @Query("select c from CheckoutProduct c where c.userId = :userId ")
    void findAllByUserId(@Param("userId") final Long userId);

    @Modifying
    @Query(value = "UPDATE checkout_product SET quantity = :quantity WHERE user_id = :userId and product_id = :productId ", nativeQuery = true)
    void setQuantity(@Param("productId") final Long productId, @Param("quantity") final Long value, @Param("userId") final Long currentUserId);
    @Query(value = "select sum(cp.quantity) from CheckoutProduct cp where cp.userId = :userId ")
    Long getCheckoutCount(@Param("userId") Long userId);
    @Query(value = " select cp from CheckoutProduct cp where cp.userId = :userId ")
    List<CheckoutProduct> findByUserId(@Param("userId") Long userId);
    @Query(value = " select cp from CheckoutProduct cp where cp.userId = :userId and cp.product.id = :productId")
    Optional<CheckoutProduct> findByProductIdUserId(@Param("productId") Long id, @Param("userId") String userId);

}
