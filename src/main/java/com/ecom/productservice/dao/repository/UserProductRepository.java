package com.ecom.productservice.dao.repository;

import com.ecom.productservice.dao.entities.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Long>, JpaSpecificationExecutor<UserProduct> {
    @Query(value = "select count(p.id) from user_product up left join product p  on p.id = up.product_id where up.user_id = :userId ", nativeQuery = true)
    Long getCheckoutCount(@Param("userId") Long userId);

}
