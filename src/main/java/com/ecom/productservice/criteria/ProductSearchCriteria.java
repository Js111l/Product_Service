package com.ecom.productservice.criteria;

import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.dao.entities.UserProduct;
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
    private final List<Long> categories;
    private final String sessionId;
    private final Boolean getFavoriteList;

    @Builder
    public ProductSearchCriteria(Integer page, Integer size, Boolean sortAsc, String sortField, Boolean bestsellerOrNewFlag, String categoryPath, List<Long> categories,
                                 String sessionId, Boolean getFavoriteList) {
        super(page, size, sortAsc, sortField);
        this.bestsellerOrNewFlag = bestsellerOrNewFlag;
        this.categoryPath = categoryPath;
        this.categories = categories;
        this.sessionId = sessionId;
        this.getFavoriteList = getFavoriteList;
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
        if (getFavoriteList != null && getFavoriteList && sessionId != null && !sessionId.isEmpty()) {
            var subQuery = query.subquery(Long.class);
            var upRoot = subQuery.from(UserProduct.class);
            subQuery.select(upRoot.get("productId"))
                    .where(cb.equal(upRoot.get("sessionId"), sessionId));

            array.add(root.get("id").in(subQuery));
        }
        return array;
    }
}
