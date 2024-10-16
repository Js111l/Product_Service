package com.ecom.productservice.criteria;

import com.ecom.productservice.dao.entities.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductSearchCriteria extends AbstractSearchCriteria<Product> {
    private final Boolean bestsellerOrNewFlag;
    private final String categoryPath;

    @Builder
    public ProductSearchCriteria(Integer page, Integer size, Boolean sortAsc, String sortField, Boolean bestsellerOrNewFlag, String categoryPath) {
        super(page, size, sortAsc, sortField);
        this.bestsellerOrNewFlag = bestsellerOrNewFlag;
        this.categoryPath = categoryPath;
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
//            final var sub = query.subquery(ProductCategory.class);
//            final var categoryRoot = sub.from(EmployeeAbsence.class);

            //raczej powinien przefiltrowac po wszystkich produktach, ktore naleza do konkretnej kategorii i sa dziecmi.
            //np. apparel.boots -> powinien wskazac wszystkie "dzieci" tej kategorii
            array.add(cb.like(root.get("productCategories").get("path"), this.categoryPath + "%"));
        }
        return array;
    }
}
