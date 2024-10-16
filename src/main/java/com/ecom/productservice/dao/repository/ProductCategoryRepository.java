package com.ecom.productservice.dao.repository;

import com.ecom.productservice.dao.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("select pc from ProductCategory pc where pc.parentCategory.id = :parentId and pc.path like concat(:parentPrefix, '%')")
    List<ProductCategory> findAllChildren(@Param("parentId") Long parentId,
                                          @Param("parentPrefix") String parentPrefix);

    @Query(value = """
            select pc.* from product_category pc where pc.parent_category_id = :parentId and pc.path like concat(:parentPrefix, '%')
            and pc.path ~ '^[a-zA-Z]+\\.[a-zA-Z]+$' 
            """, nativeQuery = true)
    List<ProductCategory> findFirstLevelChildren(@Param("parentId") Long parentId,
                                                 @Param("parentPrefix") String parentPrefix);

    @Query("select pc from ProductCategory pc where pc.parentCategory is null")
    List<ProductCategory> findParentCategories();

}
