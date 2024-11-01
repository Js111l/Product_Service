package com.ecom.productservice.dao.repository;

import com.ecom.productservice.criteria.ProductSearchCriteria;
import com.ecom.productservice.model.ReturnListModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    //query do pobrania zwrotów
    public ReturnListModel getReturnList(ProductSearchCriteria sc) {
        var baseSelect = """
                
                
                """;




        return new ReturnListModel();
    }

}
