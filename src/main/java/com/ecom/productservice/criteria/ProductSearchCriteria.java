package com.ecom.productservice.criteria;

import com.ecom.productservice.dao.entities.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ProductSearchCriteria extends AbstractSearchCriteria<Product> {

    public ProductSearchCriteria(Integer page, Integer size, Boolean sortAsc, String sortField) {
        super(page, size, sortAsc, sortField);
    }

    @Override
    public List<Predicate> getPredicates(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return List.of();
    }
}
