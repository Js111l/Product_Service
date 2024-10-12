package com.ecom.productservice.dao.repository;

import com.ecom.productservice.dao.entities.ProductImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportRepository extends JpaRepository<ProductImport, Long> {
}
