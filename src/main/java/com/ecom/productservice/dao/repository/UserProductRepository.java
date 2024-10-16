package com.ecom.productservice.dao.repository;

import com.ecom.productservice.dao.entities.UserProduct;
import com.ecom.productservice.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Long>, JpaSpecificationExecutor<UserProduct> {
    @Query("select up from UserProduct up join fetch up.products p where up.userId = :userId ")
    Page<ProductModel> findAll(@Param("userId") Long currentUser, PageRequest pageRequest);

    @Query(value = "select count(p.id) from user_product up left join product p  on p.id = up.product_id where up.user_id = :userId ", nativeQuery = true)
    Long getCheckoutCount(@Param("userId") Long userId);
}
