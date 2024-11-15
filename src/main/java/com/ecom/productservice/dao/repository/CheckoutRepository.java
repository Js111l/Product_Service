package com.ecom.productservice.dao.repository;

import com.ecom.productservice.dao.entities.CheckoutProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutProduct, Long> {
    @Query("select c from CheckoutProduct c where c.sessionId = :sessionId ")
    void findAllByUserId(@Param("sessionId") final String sessionId);

    @Modifying
    @Query(value = "UPDATE checkout_product SET quantity = :quantity WHERE session_id = :sessionId and product_id = :productId ", nativeQuery = true)
    void setQuantity(@Param("productId") final Long productId,
                     @Param("quantity") final Long value,
                     @Param("sessionId") final String sessionId);

    @Query(value = "select sum(cp.quantity) from CheckoutProduct cp where cp.sessionId = :sessionId ")
    Long getCheckoutCount(@Param("sessionId") String sessionId);

    @Query(value = " select cp from CheckoutProduct cp where cp.sessionId = :sessionId and cp.product.id = :productId")
    Optional<CheckoutProduct> findByProductIdUserId(@Param("productId") Long id, @Param("sessionId") String sessionId);

    @Modifying
    @Query("delete from CheckoutProduct cp where cp.sessionId = :sessionId and cp.product.id in (:productIds) ")
    void deleteByProductIdsUserId(@Param("productIds") List<Long> ids, @Param("sessionId") String sessionId);

    @Query(value = " select cp from CheckoutProduct cp where cp.sessionId = :sessionId ")
    List<CheckoutProduct> findByUserSessionId(@Param("sessionId") String sessionId);
}
