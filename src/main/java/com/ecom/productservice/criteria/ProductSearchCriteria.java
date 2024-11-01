package com.ecom.productservice.criteria;

import com.ecom.productservice.dao.entities.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class ProductSearchCriteria extends AbstractSearchCriteria<Product> {
    private final Boolean bestsellerOrNewFlag;
    private final String categoryPath;
    private final List<Long> categories;

    @Builder
    public ProductSearchCriteria(Integer page, Integer size, Boolean sortAsc, String sortField, Boolean bestsellerOrNewFlag, String categoryPath, List<Long> categories) {
        super(page, size, sortAsc, sortField);
        this.bestsellerOrNewFlag = bestsellerOrNewFlag;
        this.categoryPath = categoryPath;
        this.categories = categories;
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
            } else {
                //TODO bestseller
            }
        }
        if (StringUtils.isNotBlank(this.categoryPath)) {
            array.add(cb.like(root.get("productCategories").get("path"), this.categoryPath + "%"));
        }
        if (categories != null && !categories.isEmpty()) {
            array.add(root.get("productCategories").get("id").in(categories));
        }
        return array;
    }
}
