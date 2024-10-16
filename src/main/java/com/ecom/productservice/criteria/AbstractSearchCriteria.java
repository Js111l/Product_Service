package com.ecom.productservice.criteria;

import com.ecom.productservice.dao.entities.BaseEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class AbstractSearchCriteria<T extends BaseEntity> {

    private final Integer page;
    private final Integer size;
    private final Boolean sortAsc;
    private final String sortField;

    public Specification<T> getSpecification() {
        return (root, query, builder) -> {
            final var predicates = this.getPredicates(root, query, builder);
            return builder.and(predicates != null ? predicates.toArray(Predicate[]::new) : null);
        };
    }

    public abstract List<Predicate> getPredicates(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);

    public PageRequest getPageRequest() {
        final var sort = Sort.by(this.sortAsc ? Sort.Direction.ASC : Sort.Direction.DESC, this.sortField);
        return PageRequest.of(this.calculatePage(this.page, this.size), this.size, sort);
    }

    private Integer calculatePage(final int page, final int size) {
        return page / size;
    }

}
