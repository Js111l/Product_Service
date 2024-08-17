package com.ecom.productservice.criteria;

import com.ecom.productservice.dao.entities.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchCriteria extends AbstractSearchCriteria<Product> {
    private final Boolean bestsellerOrNewFlag;

    public ProductSearchCriteria(Integer page, Integer size, Boolean sortAsc, String sortField, Boolean bestsellerOrNewFlag) {
        super(page, size, sortAsc, sortField);
        this.bestsellerOrNewFlag = bestsellerOrNewFlag;
    }

    @Override
    public List<Predicate> getPredicates(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var array = new ArrayList<Predicate>();
        if (this.bestsellerOrNewFlag != null) {
            if (!bestsellerOrNewFlag) {
                array.add(cb.or(
                        cb.equal(root.get("created_date"), LocalDate.now()),
                        cb.greaterThanOrEqualTo(root.get("created_date"), LocalDate.now().minusDays(14))
                ));
            }else{
                //TODO bestseller
            }
        }
        return List.of();
    }
}
